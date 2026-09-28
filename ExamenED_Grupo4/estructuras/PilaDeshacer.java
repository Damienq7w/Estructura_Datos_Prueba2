package estructuras;

import modelo.Prestamo;

/**
 * Pila (LIFO) de devoluciones para deshacer la ultima devolucion
 * registrada con un usuario equivocado.
 */
public class PilaDeshacer {

    private NodoAccion tope;
    private int tamanio;

    public PilaDeshacer() {
        tope = null;
        tamanio = 0;
    }

    public boolean estaVacia() {
        return tope == null;
    }

    public int getTamanio() {
        return tamanio;
    }

    /** El nuevo nodo pasa a ser el tope. O(1). */
    public void apilar(Prestamo prestamo) {
        NodoAccion nuevo = new NodoAccion(prestamo);
        nuevo.siguiente = tope;
        tope = nuevo;
        tamanio++;
    }

    /** Saca y devuelve el tope. O(1). */
    public Prestamo desapilar() {
        if (estaVacia()) {
            return null;
        }
        Prestamo dato = tope.dato;
        tope = tope.siguiente;
        tamanio--;
        return dato;
    }

    /** Consulta el tope sin sacarlo. */
    public Prestamo verTope() {
        return estaVacia() ? null : tope.dato;
    }

    public void mostrar() {
        if (estaVacia()) {
            System.out.println("No hay devoluciones para deshacer.");
            return;
        }
        NodoAccion actual = tope;
        while (actual != null) {
            System.out.println((actual == tope ? "TOPE -> " : "        ") + actual.dato);
            actual = actual.siguiente;
        }
    }
}
