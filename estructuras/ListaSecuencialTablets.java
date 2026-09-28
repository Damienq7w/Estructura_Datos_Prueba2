package estructuras;

import modelo.Prestamo;
import modelo.Tablet;

/**
 * Lista secuencial basada en un arreglo de capacidad fija.
 * Se utiliza para administrar el inventario de tablets.
 * Responsable: Chalco Tasna Kenneth Mateo
 */
public class ListaSecuencialTablets {

    // Arreglo donde se almacenan las tablets del inventario.
    private Tablet[] datos;

    // Indica cuantos espacios del arreglo estan actualmente ocupados.
    private int tamanio;

    /**
     * Crea la lista secuencial con una capacidad fija.
     * Al comenzar, el inventario se encuentra vacio.
     */
    public ListaSecuencialTablets(int capacidad) {
        datos = new Tablet[capacidad];
        tamanio = 0;
    }

    /**
     * Comprueba si actualmente no existen tablets registradas.
     */
    public boolean estaVacia() {
        return tamanio == 0;
    }

    /**
     * Comprueba si todos los espacios del arreglo estan ocupados.
     */
    public boolean estaLlena() {
        return tamanio == datos.length;
    }

    /**
     * Devuelve la cantidad actual de tablets registradas.
     */
    public int getTamanio() {
        return tamanio;
    }

    /**
     * Inserta una tablet al final de la lista secuencial.
     * No permite insertar si el arreglo esta lleno
     * o si ya existe una tablet con el mismo codigo.
     *
     * La verificacion del codigo requiere una busqueda
     * secuencial, por lo que puede tener complejidad O(n).
     */
    public boolean insertar(Tablet tablet) {
        if (estaLlena() || buscar(tablet.getCodigo()) != null) {
            return false;
        }

        // Se agrega la nueva tablet en la primera posicion libre.
        datos[tamanio] = tablet;

        // Se incrementa la cantidad de elementos almacenados.
        tamanio++;

        return true;
    }

    /**
     * Realiza una busqueda secuencial utilizando el codigo.
     * Recorre solamente desde la posicion 0 hasta tamanio - 1.
     *
     * Complejidad: O(n).
     */
    public Tablet buscar(String codigo) {
        for (int i = 0; i < tamanio; i++) {

            // equalsIgnoreCase permite buscar sin importar
            // diferencias entre mayusculas y minusculas.
            if (datos[i].getCodigo().equalsIgnoreCase(codigo)) {
                return datos[i];
            }
        }

        // Si termina el recorrido y no se encuentra, retorna null.
        return null;
    }

    /**
     * Muestra todas las tablets actualmente almacenadas
     * dentro del inventario.
     */
    public void mostrar() {

        // Evita realizar un recorrido innecesario si no existen datos.
        if (estaVacia()) {
            System.out.println("No hay tablets registradas.");
            return;
        }

        // Encabezados utilizados para ordenar la salida en consola.
        System.out.println("Codigo  | Marca     | Almacen. | Version      | Estado");
        System.out.println("--------+-----------+----------+--------------+--------------");

        // Se recorren solamente las posiciones que contienen tablets.
        for (int i = 0; i < tamanio; i++) {
            System.out.println(datos[i]);
        }

        System.out.println("Total de tablets: " + tamanio);
    }

    /**
     * Modifica el estado de una tablet utilizando su codigo.
     * Solo realiza el cambio si la tablet existe
     * y el nuevo estado pertenece a los estados permitidos.
     */
    public boolean modificarEstado(String codigo, String nuevoEstado) {

        // Primero se busca la tablet dentro del inventario.
        Tablet tablet = buscar(codigo);

        // Se valida que exista y que el nuevo estado sea correcto.
        if (tablet == null || !Tablet.esEstadoValido(nuevoEstado)) {
            return false;
        }

        // Se modifica solamente el estado de la tablet encontrada.
        tablet.setEstado(nuevoEstado);

        return true;
    }

    /**
     * Elimina una tablet del inventario.
     *
     * Una tablet PRESTADA no puede eliminarse porque
     * existe un prestamo activo asociado a ella.
     *
     * Luego de eliminar, los elementos posteriores se
     * desplazan una posicion hacia la izquierda para evitar
     * espacios vacios dentro de la lista secuencial.
     *
     * Complejidad: O(n).
     */
    public boolean eliminar(String codigo) {

        // Busca directamente la posicion de la tablet.
        for (int i = 0; i < tamanio; i++) {

            if (datos[i].getCodigo().equalsIgnoreCase(codigo)) {

                // Una tablet prestada no puede eliminarse.
                if (Tablet.PRESTADA.equals(datos[i].getEstado())) {
                    return false;
                }

                /**
                 * Corrimiento de elementos.
                 *
                 * El elemento siguiente ocupa la posicion
                 * del elemento eliminado para que no queden
                 * espacios intermedios en el arreglo.
                 */
                for (int j = i; j < tamanio - 1; j++) {
                    datos[j] = datos[j + 1];
                }

                // La ultima posicion utilizada queda vacia.
                datos[tamanio - 1] = null;

                // Se reduce la cantidad de tablets registradas.
                tamanio--;

                return true;
            }
        }

        // Retorna false cuando no se encuentra el codigo.
        return false;
    }

    /**
     * Busca la primera tablet que se encuentre DISPONIBLE
     * y que sea compatible con el tipo de usuario.
     *
     * Aqui se aplica la regla diferenciadora del Grupo 4:
     * un GRUPO_INVESTIGACION no puede recibir una tablet
     * con menos de 32 GB de almacenamiento.
     */
    public Tablet buscarDisponible(String tipoUsuario) {

        // Recorre el inventario desde el primer elemento.
        for (int i = 0; i < tamanio; i++) {

            Tablet t = datos[i];

            // Si la tablet no esta disponible, se ignora
            // y se continua buscando la siguiente.
            if (!Tablet.DISPONIBLE.equals(t.getEstado())) {
                continue;
            }

            /**
             * Regla de negocio de 32 GB.
             *
             * Si quien solicita es un grupo de investigacion
             * y la tablet tiene menos de 32 GB, esa tablet
             * no puede ser asignada.
             */
            if (Prestamo.GRUPO_INVESTIGACION.equals(tipoUsuario) && t.getAlmacenamientoGB() < 32) {
                continue;
            }

            // Devuelve la primera tablet que cumple las condiciones.
            return t;
        }

        // No existe ninguna tablet compatible disponible.
        return null;
    }

    /**
     * Carga automaticamente tablets de prueba.
     *
     * TAB001, TAB002 y TAB003 corresponden a los datos
     * planteados para el Grupo 4.
     *
     * TAB004 posee 16 GB y permite demostrar la aplicacion
     * de la regla diferenciadora de los 32 GB.
     */
    public void cargarDatosPrueba() {

        insertar(new Tablet(
                "TAB001",
                "Samsung",
                64,
                "Android 13",
                Tablet.DISPONIBLE));

        insertar(new Tablet(
                "TAB002",
                "Lenovo",
                32,
                "Android 12",
                Tablet.PRESTADA));

        insertar(new Tablet(
                "TAB003",
                "Huawei",
                128,
                "HarmonyOS 3",
                Tablet.DISPONIBLE));

        insertar(new Tablet(
                "TAB004",
                "Xiaomi",
                16,
                "Android 11",
                Tablet.DISPONIBLE));
    }
}