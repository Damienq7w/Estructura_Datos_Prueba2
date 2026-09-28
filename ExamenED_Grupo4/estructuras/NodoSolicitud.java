package estructuras;

import modelo.Solicitud;

/**
 * Nodo de la cola de solicitudes.
 * Responsable: Tisalema Guashco Darwin Joel
 */
public class NodoSolicitud {

    Solicitud dato;
    NodoSolicitud siguiente;

    public NodoSolicitud(Solicitud dato) {
        this.dato = dato;
        this.siguiente = null;
    }
}
