package estructuras;

import modelo.Solicitud;

/**
 * Cola (FIFO) de solicitudes en espera cuando todas las tablets estan ocupadas.
 */
public class ColaSolicitudes {

    private NodoSolicitud frente;
    private NodoSolicitud fin;
    private int tamanio;

    public ColaSolicitudes() {
        frente = null;
        fin = null;
        tamanio = 0;
    }

    public boolean estaVacia() {
        return frente == null;
    }

    public int getTamanio() {
        return tamanio;
    }

    /** Agrega por el final. O(1) gracias a la referencia fin. */
    public void encolar(Solicitud solicitud) {
        NodoSolicitud nuevo = new NodoSolicitud(solicitud);
        if (estaVacia()) {
            frente = nuevo;
        } else {
            fin.siguiente = nuevo;
        }
        fin = nuevo;
        tamanio++;
    }

    /** Saca y devuelve la solicitud del frente. O(1). */
    public Solicitud desencolar() {
        if (estaVacia()) {
            return null;
        }
        Solicitud atendida = frente.dato;
        frente = frente.siguiente;
        if (frente == null) {
            fin = null;
        }
        tamanio--;
        return atendida;
    }

    /** Consulta el frente sin sacarlo. */
    public Solicitud verFrente() {
        return estaVacia() ? null : frente.dato;
    }

    public boolean existeCedula(String cedula) {
        NodoSolicitud actual = frente;
        while (actual != null) {
            if (actual.dato.getCedula().equals(cedula)) {
                return true;
            }
            actual = actual.siguiente;
        }
        return false;
    }

    /** Lista las solicitudes en orden de llegada. */
    public void listar() {
        if (estaVacia()) {
            System.out.println("No hay solicitudes en espera.");
            return;
        }
        NodoSolicitud actual = frente;
        int posicion = 1;
        while (actual != null) {
            System.out.println(posicion + ". " + actual.dato + (posicion == 1 ? "  <- FRENTE" : ""));
            actual = actual.siguiente;
            posicion++;
        }
        System.out.println("Solicitudes en espera: " + tamanio);
    }
}
