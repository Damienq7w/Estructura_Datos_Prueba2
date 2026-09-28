package estructuras;

/**
 * Lista circular simplemente enlazada para los turnos de lectura
 * en la tablet de consulta rapida. El ultimo nodo apunta al primero.
 * Responsable: Tacuri Santillan Monica Sara
 */
public class ListaCircularTurnos {

    private NodoTurno actual;
    private int tamanio;

    public ListaCircularTurnos() {
        actual = null;
        tamanio = 0;
    }

    public boolean estaVacia() {
        return actual == null;
    }

    public int getTamanio() {
        return tamanio;
    }

    public String getLectorActual() {
        return estaVacia() ? null : actual.lector;
    }

    /** Devuelve el nodo cuyo siguiente es 'actual' (el ultimo de la ronda). O(n). */
    private NodoTurno buscarAnteriorAlActual() {
        NodoTurno aux = actual;
        while (aux.siguiente != actual) {
            aux = aux.siguiente;
        }
        return aux;
    }

    /** Inserta al final de la ronda (justo antes del turno actual). */
    public void insertar(String lector) {
        NodoTurno nuevo = new NodoTurno(lector);
        if (estaVacia()) {
            nuevo.siguiente = nuevo;
            actual = nuevo;
        } else {
            NodoTurno ultimo = buscarAnteriorAlActual();
            ultimo.siguiente = nuevo;
            nuevo.siguiente = actual;
        }
        tamanio++;
    }

    /** Pasa el turno al siguiente lector. Despues del ultimo vuelve al primero. O(1). */
    public String avanzar() {
        if (estaVacia()) {
            return null;
        }
        actual = actual.siguiente;
        return actual.lector;
    }

    /** Elimina al lector que tiene el turno sin romper la circularidad. */
    public String eliminarActual() {
        if (estaVacia()) {
            return null;
        }
        String eliminado = actual.lector;
        if (actual.siguiente == actual) {
            actual = null;
        } else {
            NodoTurno anterior = buscarAnteriorAlActual();
            anterior.siguiente = actual.siguiente;
            actual = actual.siguiente;
        }
        tamanio--;
        return eliminado;
    }

    /** Muestra una vuelta completa desde el turno actual (do-while evita el bucle infinito). */
    public void mostrarRonda() {
        if (estaVacia()) {
            System.out.println("No hay lectores en la ronda.");
            return;
        }
        NodoTurno aux = actual;
        int posicion = 1;
        do {
            System.out.println(posicion + ". " + aux.lector + (aux == actual ? "  <- TURNO ACTUAL" : ""));
            aux = aux.siguiente;
            posicion++;
        } while (aux != actual);
        System.out.println("(despues de " + buscarAnteriorAlActual().lector + " vuelve " + actual.lector + ")");
    }
}
