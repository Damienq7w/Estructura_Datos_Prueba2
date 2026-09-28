package servicio;

import estructuras.ColaSolicitudes;
import estructuras.ListaCircularTurnos;
import estructuras.ListaDobleHistorial;
import estructuras.ListaSecuencialTablets;
import estructuras.ListaSimplePrestamos;
import estructuras.PilaDeshacer;
import modelo.Movimiento;
import modelo.Prestamo;
import modelo.Solicitud;
import modelo.Tablet;

/**
 * Integra las seis estructuras y aplica las reglas del negocio.
 * Toda operacion importante queda registrada en el historial.
 * Responsable: Cunalata Mendoza Damian Alexander
 */
public class BibliotecaService {

    private final ListaSecuencialTablets inventario = new ListaSecuencialTablets(20);
    private final ListaSimplePrestamos prestamos = new ListaSimplePrestamos();
    private final ColaSolicitudes cola = new ColaSolicitudes();
    private final ListaDobleHistorial historial = new ListaDobleHistorial();
    private final PilaDeshacer pila = new PilaDeshacer();
    private final ListaCircularTurnos turnos = new ListaCircularTurnos();

    public ListaSecuencialTablets getInventario() {
        return inventario;
    }

    public ListaSimplePrestamos getPrestamos() {
        return prestamos;
    }

    public ColaSolicitudes getCola() {
        return cola;
    }

    public ListaDobleHistorial getHistorial() {
        return historial;
    }

    public PilaDeshacer getPila() {
        return pila;
    }

    public ListaCircularTurnos getTurnos() {
        return turnos;
    }

    private void registrar(String tipo, String descripcion) {
        historial.insertarFinal(new Movimiento(tipo, descripcion));
    }

    public void cargarDatosPrueba() {
        inventario.cargarDatosPrueba();
        prestamos.insertar(new Prestamo("1804567890", "Maria Lopez", Prestamo.ESTUDIANTE, "TAB002"));
        registrar("CARGA", "Datos de prueba: 4 tablets y prestamo activo de TAB002 (cedula 1804567890)");
    }

    // Inventario, sirve para registrar, modificar estado y eliminar tablets.

    public String registrarTablet(String codigo, String marca, int almacenamiento, String version) {
        if (inventario.buscar(codigo) != null) {
            return "ERROR: ya existe una tablet con el codigo " + codigo + ".";
        }
        if (inventario.estaLlena()) {
            return "ERROR: el inventario esta lleno.";
        }
        inventario.insertar(new Tablet(codigo, marca, almacenamiento, version, Tablet.DISPONIBLE));
        registrar("REGISTRO", "Tablet " + codigo + " (" + marca + ", " + almacenamiento + " GB) registrada");
        return "Tablet " + codigo + " registrada como DISPONIBLE.";
    }

    public String modificarEstadoTablet(String codigo, String nuevoEstado) {
        Tablet tablet = inventario.buscar(codigo);
        if (tablet == null) {
            return "ERROR: no existe la tablet " + codigo + ".";
        }
        if (Tablet.PRESTADA.equals(tablet.getEstado())) {
            return "ERROR: la tablet " + codigo + " esta prestada. Primero registre la devolucion.";
        }
        if (Tablet.PRESTADA.equals(nuevoEstado)) {
            return "ERROR: una tablet solo pasa a PRESTADA mediante un prestamo.";
        }
        if (tablet.getEstado().equals(nuevoEstado)) {
            return "La tablet " + codigo + " ya esta en estado " + nuevoEstado + ".";
        }
        String anterior = tablet.getEstado();
        inventario.modificarEstado(codigo, nuevoEstado);
        registrar("ESTADO", "Tablet " + codigo + ": " + anterior + " -> " + nuevoEstado);
        return "Estado de " + codigo + " cambiado a " + nuevoEstado + ".";
    }

    public String eliminarTablet(String codigo) {
        Tablet tablet = inventario.buscar(codigo);
        if (tablet == null) {
            return "ERROR: no existe la tablet " + codigo + ".";
        }
        if (!inventario.eliminar(codigo)) {
            return "ERROR: no se puede eliminar " + codigo + " porque esta PRESTADA.";
        }
        registrar("ELIMINACION", "Tablet " + codigo + " eliminada del inventario");
        return "Tablet " + codigo + " eliminada del inventario.";
    }

    // Prestamos, sirve para prestar y devolver tablets, y atender solicitudes en cola.

    public String prestar(String cedula, String nombre, String tipoUsuario) {
        if (prestamos.existeCedula(cedula)) {
            return "ERROR: la cedula " + cedula + " ya tiene un prestamo activo.";
        }
        if (cola.existeCedula(cedula)) {
            return "ERROR: la cedula " + cedula + " ya tiene una solicitud en espera.";
        }
        Tablet tablet = inventario.buscarDisponible(tipoUsuario);
        if (tablet != null) {
            return asignar(tablet, new Prestamo(cedula, nombre, tipoUsuario, tablet.getCodigo()), "PRESTAMO");
        }

        cola.encolar(new Solicitud(cedula, nombre, tipoUsuario));
        registrar("COLA", "Solicitud de " + nombre + " (" + cedula + ") enviada a la cola");
        String motivo;
        if (Prestamo.GRUPO_INVESTIGACION.equals(tipoUsuario)
                && inventario.buscarDisponible(Prestamo.ESTUDIANTE) != null) {
            Tablet pequena = inventario.buscarDisponible(Prestamo.ESTUDIANTE);
            motivo = "REGLA: " + pequena.getCodigo() + " (" + pequena.getAlmacenamientoGB()
                    + " GB) no puede asignarse a grupos de investigacion (minimo 32 GB).";
        } else {
            motivo = "Todas las tablets estan ocupadas.";
        }
        return motivo + "\nSolicitud enviada a la cola (posicion " + cola.getTamanio() + ").";
    }

    private String asignar(Tablet tablet, Prestamo prestamo, String tipoMovimiento) {
        inventario.modificarEstado(tablet.getCodigo(), Tablet.PRESTADA);
        prestamos.insertar(prestamo);
        registrar(tipoMovimiento, "Tablet " + tablet.getCodigo() + " prestada a "
                + prestamo.getNombre() + " (" + prestamo.getCedula() + ", " + prestamo.getTipoUsuario() + ")");
        return "Prestamo registrado: tablet " + tablet.getCodigo() + " (" + tablet.getAlmacenamientoGB()
                + " GB) asignada a " + prestamo.getNombre() + ".";
    }

    public String devolver(String cedula) {
        Prestamo prestamo = prestamos.eliminar(cedula);
        if (prestamo == null) {
            return "ERROR: no existe un prestamo activo con la cedula " + cedula + ".";
        }
        inventario.modificarEstado(prestamo.getCodigoTablet(), Tablet.DISPONIBLE);
        pila.apilar(prestamo);
        registrar("DEVOLUCION", "Tablet " + prestamo.getCodigoTablet() + " devuelta por "
                + prestamo.getNombre() + " (" + cedula + ")");
        String mensaje = "Devolucion registrada: tablet " + prestamo.getCodigoTablet() + " ahora DISPONIBLE.";
        if (!cola.estaVacia()) {
            mensaje += "\nHay " + cola.getTamanio() + " solicitud(es) en espera. Use la opcion 5 para atenderlas.";
        }
        return mensaje;
    }

    // Cola, sirve para atender solicitudes de prestamo en orden de llegada.


    public String atenderSiguienteSolicitud() {
        if (cola.estaVacia()) {
            return "No hay solicitudes en espera.";
        }
        Solicitud frente = cola.verFrente();
        if (prestamos.existeCedula(frente.getCedula())) {
            cola.desencolar();
            registrar("COLA", "Solicitud de " + frente.getCedula() + " descartada: ya tiene prestamo activo");
            return "La solicitud de " + frente.getNombre() + " se descarto porque ya tiene un prestamo activo.";
        }
        Tablet tablet = inventario.buscarDisponible(frente.getTipoUsuario());
        if (tablet == null) {
            return "No hay una tablet disponible compatible para " + frente.getNombre()
                    + " (" + frente.getTipoUsuario() + "). La solicitud sigue en el frente.";
        }
        cola.desencolar();
        return "Solicitud atendida. " + asignar(tablet, new Prestamo(frente.getCedula(), frente.getNombre(),
                frente.getTipoUsuario(), tablet.getCodigo()), "ATENCION COLA");
    }

    // Deshacer, sirve para revertir la ultima devolucion, si es posible.

    public String deshacerUltimaDevolucion() {
        if (pila.estaVacia()) {
            return "No hay devoluciones para deshacer.";
        }
        Prestamo ultima = pila.verTope();
        Tablet tablet = inventario.buscar(ultima.getCodigoTablet());
        if (tablet == null || !Tablet.DISPONIBLE.equals(tablet.getEstado())) {
            return "No se puede deshacer: la tablet " + ultima.getCodigoTablet()
                    + " ya no esta DISPONIBLE (fue prestada, eliminada o enviada a mantenimiento).";
        }
        if (prestamos.existeCedula(ultima.getCedula())) {
            return "No se puede deshacer: la cedula " + ultima.getCedula() + " ya tiene otro prestamo activo.";
        }
        pila.desapilar();
        inventario.modificarEstado(tablet.getCodigo(), Tablet.PRESTADA);
        prestamos.insertar(ultima);
        registrar("DESHACER", "Se revirtio la devolucion de " + tablet.getCodigo()
                + ": prestamo de " + ultima.getNombre() + " (" + ultima.getCedula() + ") restaurado");
        return "Devolucion deshecha: el prestamo de " + ultima.getNombre() + " con la tablet "
                + tablet.getCodigo() + " vuelve a estar activo.";
    }

    // Mantenimiento, sirve para enviar y retornar tablets a mantenimiento.

    public String enviarMantenimiento(String codigo) {
        return modificarEstadoTablet(codigo, Tablet.MANTENIMIENTO);
    }

    public String retornarMantenimiento(String codigo) {
        Tablet tablet = inventario.buscar(codigo);
        if (tablet == null) {
            return "ERROR: no existe la tablet " + codigo + ".";
        }
        if (!Tablet.MANTENIMIENTO.equals(tablet.getEstado())) {
            return "ERROR: la tablet " + codigo + " no esta en mantenimiento.";
        }
        return modificarEstadoTablet(codigo, Tablet.DISPONIBLE);
    }

    // Turnos, sirve para agregar lectores a la ronda, avanzar el turno y eliminar el turno actual.

    public String agregarLector(String lector) {
        turnos.insertar(lector);
        registrar("TURNO", lector + " agregado a la ronda de la tablet de consulta rapida");
        return lector + " agregado a la ronda (" + turnos.getTamanio() + " lector(es)).";
    }

    public String avanzarTurno() {
        String lector = turnos.avanzar();
        if (lector == null) {
            return "No hay lectores en la ronda.";
        }
        registrar("TURNO", "Turno de lectura para " + lector);
        return "Turno de: " + lector;
    }

    public String eliminarTurnoActual() {
        String eliminado = turnos.eliminarActual();
        if (eliminado == null) {
            return "No hay lectores en la ronda.";
        }
        registrar("TURNO", eliminado + " salio de la ronda");
        String mensaje = eliminado + " salio de la ronda.";
        if (!turnos.estaVacia()) {
            mensaje += " Ahora es el turno de: " + turnos.getLectorActual();
        }
        return mensaje;
    }
}
