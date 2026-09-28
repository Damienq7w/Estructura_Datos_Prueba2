package modelo;

/**
 * Solicitud de tablet que queda en espera cuando no hay tablets disponibles.
 * Responsable: Tisalema Guashco Darwin Joel
 */
public class Solicitud {

    private String cedula;
    private String nombre;
    private String tipoUsuario;

    public Solicitud(String cedula, String nombre, String tipoUsuario) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.tipoUsuario = tipoUsuario;
    }

    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    @Override
    public String toString() {
        return String.format("Cedula: %s | %-20s | %s", cedula, nombre, tipoUsuario);
    }
}
