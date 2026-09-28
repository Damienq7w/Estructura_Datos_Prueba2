package estructuras;

/**
 * Nodo de la lista circular de turnos de lectura.
 * Responsable: Tacuri Santillan Monica Sara
 */
public class NodoTurno {

    String lector;
    NodoTurno siguiente;

    public NodoTurno(String lector) {
        this.lector = lector;
        this.siguiente = null;
    }
}
