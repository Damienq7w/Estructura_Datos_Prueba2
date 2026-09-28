package estructuras;

import modelo.Prestamo;

/**
 * Nodo de la pila de deshacer. Guarda el prestamo que fue devuelto.
 */
public class NodoAccion {

    // Préstamo almacenado en el nodo
    Prestamo dato;

    // Referencia al siguiente nodo de la pila
    NodoAccion siguiente;

    // Inicializa el nodo con un préstamo
    public NodoAccion(Prestamo dato) {
        this.dato = dato;
        this.siguiente = null;
    }
}