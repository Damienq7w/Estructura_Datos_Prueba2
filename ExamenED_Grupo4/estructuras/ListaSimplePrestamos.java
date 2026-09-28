package estructuras;

import modelo.Prestamo;

/**
 * Gestiona una lista simplemente enlazada de prestamos activos asociados a cedula.
 * Responsable: Silva Camuendo Luis Alexander
 */
public class ListaSimplePrestamos {

    private NodoPrestamo cabeza;
    private int tamanio;

    public ListaSimplePrestamos() {
        cabeza = null;
        tamanio = 0;
    }

    public boolean estaVacia() {
        return cabeza == null;
    }

    public int getTamanio() {
        return tamanio;
    }

    /** Agrega un nuevo prestamo al final de la lista: recorre hasta el ultimo nodo y engancha el nuevo. O(n). */
    public void insertar(Prestamo prestamo) {
        NodoPrestamo nuevo = new NodoPrestamo(prestamo);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            NodoPrestamo actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }
        tamanio++;
    }

    /** Recorre la lista para encontrar un prestamo mediante el numero de cedula. O(n). */
    public Prestamo buscar(String cedula) {
        NodoPrestamo actual = cabeza;
        while (actual != null) {
            if (actual.dato.getCedula().equals(cedula)) {
                return actual.dato;
            }
            actual = actual.siguiente;
        }
        return null;
    }

    public boolean existeCedula(String cedula) {
        return buscar(cedula) != null;
    }

    /**
     * Busca y elimina el prestamo de una cedula y lo devuelve (se necesita para la pila).
     * Casos: lista vacia, el nodo es la cabeza, el nodo esta en medio o al final. O(n).
     */
    public Prestamo eliminar(String cedula) {
        if (cabeza == null) {
            return null;
        }
        if (cabeza.dato.getCedula().equals(cedula)) {
            Prestamo eliminado = cabeza.dato;
            cabeza = cabeza.siguiente;
            tamanio--;
            return eliminado;
        }
        NodoPrestamo anterior = cabeza;
        while (anterior.siguiente != null) {
            if (anterior.siguiente.dato.getCedula().equals(cedula)) {
                Prestamo eliminado = anterior.siguiente.dato;
                anterior.siguiente = anterior.siguiente.siguiente;
                tamanio--;
                return eliminado;
            }
            anterior = anterior.siguiente;
        }
        return null;
    }

    /** Recorre la lista desde la cabeza e imprime cada prestamo. */
    public void recorrer() {
        if (estaVacia()) {
            System.out.println("No hay prestamos activos.");
            return;
        }
        NodoPrestamo actual = cabeza;
        int i = 1;
        while (actual != null) {
            System.out.println(i + ". " + actual.dato);
            actual = actual.siguiente;
            i++;
        }
        System.out.println("Total de prestamos activos: " + tamanio);
    }
}
