package estructuras;

import modelo.Prestamo;

/**
 * Nodo de la lista simplemente enlazada de prestamos.
 * Responsable: Silva Camuendo Luis Alexander
 */
public class NodoPrestamo {

    Prestamo dato;
    NodoPrestamo siguiente;

    public NodoPrestamo(Prestamo dato) {
        this.dato = dato;
        this.siguiente = null;
    }
}
