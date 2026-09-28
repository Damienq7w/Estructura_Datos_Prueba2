package estructuras;

import modelo.Movimiento;

/**
 * Lista doblemente enlazada con cabeza y cola para el historial
 * de prestamos, devoluciones y demas movimientos.
 * Responsable: Tacuri Santillan Monica Sara
 */
public class ListaDobleHistorial {

    private NodoHistorial cabeza;
    private NodoHistorial cola;
    private int tamanio;

    public ListaDobleHistorial() {
        cabeza = null;
        cola = null;
        tamanio = 0;
    }

    public boolean estaVacia() {
        return cabeza == null;
    }

    public int getTamanio() {
        return tamanio;
    }

    /** Inserta al final enlazando anterior y siguiente. O(1) gracias a la referencia cola. */
    public void insertarFinal(Movimiento movimiento) {
        NodoHistorial nuevo = new NodoHistorial(movimiento);
        if (estaVacia()) {
            cabeza = nuevo;
        } else {
            nuevo.anterior = cola;
            cola.siguiente = nuevo;
        }
        cola = nuevo;
        tamanio++;
    }

    /** Del movimiento mas antiguo al mas reciente. */
    public void recorrerAdelante() {
        if (estaVacia()) {
            System.out.println("El historial esta vacio.");
            return;
        }
        NodoHistorial actual = cabeza;
        int i = 1;
        while (actual != null) {
            System.out.println(i + ". " + actual.dato);
            actual = actual.siguiente;
            i++;
        }
    }

    /** Del movimiento mas reciente al mas antiguo. */
    public void recorrerAtras() {
        if (estaVacia()) {
            System.out.println("El historial esta vacio.");
            return;
        }
        NodoHistorial actual = cola;
        int i = tamanio;
        while (actual != null) {
            System.out.println(i + ". " + actual.dato);
            actual = actual.anterior;
            i--;
        }
    }
}
