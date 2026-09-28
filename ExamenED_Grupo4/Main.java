import java.util.Scanner;
import modelo.Tablet;
import servicio.BibliotecaService;
import servicio.Validador;

/**
 * Sistema de Tablets para Biblioteca Digital - Grupo 4.
 * Menu principal de consola.
 * Responsable: Cunalata Mendoza Damian Alexander
 */
public class Main {

    private static final Scanner sc = new Scanner(System.in);
    private static final BibliotecaService biblioteca = new BibliotecaService();

    public static void main(String[] args) {
        biblioteca.cargarDatosPrueba();
        int opcion;
        do {
            mostrarMenu();
            opcion = Validador.leerEntero(sc, "Seleccione una opcion: ", 0, 9);
            System.out.println();
            switch (opcion) {
                case 1 -> menuInventario();
                case 2 -> prestar();
                case 3 -> devolver();
                case 4 -> biblioteca.getPrestamos().recorrer();
                case 5 -> menuCola();
                case 6 -> menuHistorial();
                case 7 -> menuTurnos();
                case 8 -> menuMantenimiento();
                case 9 -> deshacer();
                case 0 -> System.out.println("Gracias por usar el sistema. Hasta pronto.");
                default -> System.out.println("Opcion no valida.");
            }
        } while (opcion != 0);
        sc.close();
    }

    private static void mostrarMenu() {
        System.out.println();
        System.out.println("==================================================");
        System.out.println("   SISTEMA DE TABLETS - BIBLIOTECA DIGITAL (G4)");
        System.out.println("==================================================");
        System.out.println(" 1. Inventario de tablets");
        System.out.println(" 2. Prestar tablet");
        System.out.println(" 3. Devolver tablet");
        System.out.println(" 4. Prestamos activos");
        System.out.println(" 5. Cola de solicitudes");
        System.out.println(" 6. Historial");
        System.out.println(" 7. Turnos de lectura (tablet de consulta rapida)");
        System.out.println(" 8. Mantenimiento");
        System.out.println(" 9. Deshacer ultima devolucion");
        System.out.println(" 0. Salir");
        System.out.println("--------------------------------------------------");
    }

    // 1. Inventario

    private static void menuInventario() {
        int op;
        do {
            System.out.println("\n--- INVENTARIO (lista secuencial) ---");
            System.out.println("1. Registrar tablet");
            System.out.println("2. Buscar tablet");
            System.out.println("3. Mostrar inventario");
            System.out.println("4. Modificar estado");
            System.out.println("5. Eliminar tablet");
            System.out.println("0. Volver");
            op = Validador.leerEntero(sc, "Opcion: ", 0, 5);
            switch (op) {
                case 1 -> {
                    String codigo = Validador.leerCodigo(sc);
                    String marca = Validador.leerTexto(sc, "Marca: ");
                    int gb = Validador.leerEntero(sc, "Almacenamiento en GB (1-2048): ", 1, 2048);
                    String version = Validador.leerTexto(sc, "Version del sistema: ");
                    System.out.println(biblioteca.registrarTablet(codigo, marca, gb, version));
                }
                case 2 -> {
                    String codigo = Validador.leerCodigo(sc);
                    Tablet t = biblioteca.getInventario().buscar(codigo);
                    System.out.println(t == null ? "No existe la tablet " + codigo + "." : t.toString());
                }
                case 3 -> biblioteca.getInventario().mostrar();
                case 4 -> {
                    String codigo = Validador.leerCodigo(sc);
                    System.out.println("Nuevo estado: 1. DISPONIBLE  2. MANTENIMIENTO");
                    int e = Validador.leerEntero(sc, "Seleccione: ", 1, 2);
                    String estado = e == 1 ? Tablet.DISPONIBLE : Tablet.MANTENIMIENTO;
                    System.out.println(biblioteca.modificarEstadoTablet(codigo, estado));
                }
                case 5 -> System.out.println(biblioteca.eliminarTablet(Validador.leerCodigo(sc)));
                default -> { }
            }
        } while (op != 0);
    }

    // 2 y 3. Prestamos

    private static void prestar() {
        System.out.println("--- PRESTAR TABLET ---");
        String cedula = Validador.leerCedula(sc);
        String nombre = Validador.leerTexto(sc, "Nombre: ");
        String tipo = Validador.leerTipoUsuario(sc);
        System.out.println(biblioteca.prestar(cedula, nombre, tipo));
    }

    private static void devolver() {
        System.out.println("--- DEVOLVER TABLET ---");
        System.out.println(biblioteca.devolver(Validador.leerCedula(sc)));
    }

    // 5. Cola

    private static void menuCola() {
        int op;
        do {
            System.out.println("\n--- COLA DE SOLICITUDES ---");
            System.out.println("1. Consultar frente");
            System.out.println("2. Listar solicitudes");
            System.out.println("3. Atender siguiente solicitud");
            System.out.println("0. Volver");
            op = Validador.leerEntero(sc, "Opcion: ", 0, 3);
            switch (op) {
                case 1 -> {
                    var frente = biblioteca.getCola().verFrente();
                    System.out.println(frente == null ? "No hay solicitudes en espera." : "Frente: " + frente);
                }
                case 2 -> biblioteca.getCola().listar();
                case 3 -> System.out.println(biblioteca.atenderSiguienteSolicitud());
                default -> { }
            }
        } while (op != 0);
    }

    // 6. Historial

    private static void menuHistorial() {
        int op;
        do {
            System.out.println("\n--- HISTORIAL (lista doble) ---");
            System.out.println("1. Recorrer hacia adelante (mas antiguo -> mas reciente)");
            System.out.println("2. Recorrer hacia atras (mas reciente -> mas antiguo)");
            System.out.println("0. Volver");
            op = Validador.leerEntero(sc, "Opcion: ", 0, 2);
            switch (op) {
                case 1 -> biblioteca.getHistorial().recorrerAdelante();
                case 2 -> biblioteca.getHistorial().recorrerAtras();
                default -> { }
            }
        } while (op != 0);
    }

    // 7. Turnos

    private static void menuTurnos() {
        int op;
        do {
            System.out.println("\n--- TURNOS DE LECTURA (lista circular) ---");
            System.out.println("1. Agregar lector");
            System.out.println("2. Avanzar turno");
            System.out.println("3. Eliminar turno actual");
            System.out.println("4. Mostrar ronda");
            System.out.println("0. Volver");
            op = Validador.leerEntero(sc, "Opcion: ", 0, 4);
            switch (op) {
                case 1 -> System.out.println(biblioteca.agregarLector(Validador.leerTexto(sc, "Nombre del lector: ")));
                case 2 -> System.out.println(biblioteca.avanzarTurno());
                case 3 -> System.out.println(biblioteca.eliminarTurnoActual());
                case 4 -> biblioteca.getTurnos().mostrarRonda();
                default -> { }
            }
        } while (op != 0);
    }

    // 8. Mantenimiento

    private static void menuMantenimiento() {
        int op;
        do {
            System.out.println("\n--- MANTENIMIENTO ---");
            System.out.println("1. Enviar tablet a mantenimiento");
            System.out.println("2. Retornar tablet de mantenimiento");
            System.out.println("0. Volver");
            op = Validador.leerEntero(sc, "Opcion: ", 0, 2);
            switch (op) {
                case 1 -> System.out.println(biblioteca.enviarMantenimiento(Validador.leerCodigo(sc)));
                case 2 -> System.out.println(biblioteca.retornarMantenimiento(Validador.leerCodigo(sc)));
                default -> { }
            }
        } while (op != 0);
    }

    // 9. Deshacer

    private static void deshacer() {
        System.out.println("--- DESHACER ULTIMA DEVOLUCION (pila) ---");
        var tope = biblioteca.getPila().verTope();
        if (tope != null) {
            System.out.println("Tope de la pila: " + tope);
        }
        System.out.println(biblioteca.deshacerUltimaDevolucion());
    }
}
