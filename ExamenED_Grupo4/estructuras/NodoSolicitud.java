package estructuras;

import modelo.Solicitud;

/**
 * Nodo de la cola de solicitudes.
 */
public class NodoSolicitud {

    // Solicitud almacenada en el nodo
    Solicitud dato;

    // Referencia al siguiente nodo de la cola
    NodoSolicitud siguiente;

    public NodoSolicitud(Solicitud dato) {
        this.dato = dato;
        this.siguiente = null;
    }
}