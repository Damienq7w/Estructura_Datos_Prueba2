package estructuras;

import modelo.Movimiento;

/**
 * Nodo de la lista doblemente enlazada del historial.
 * Responsable: Tacuri Santillan Monica Sara
 */
public class NodoHistorial {

    Movimiento dato;
    NodoHistorial anterior;
    NodoHistorial siguiente;

    public NodoHistorial(Movimiento dato) {
        this.dato = dato;
        this.anterior = null;
        this.siguiente = null;
    }
}