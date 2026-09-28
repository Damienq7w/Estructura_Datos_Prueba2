package modelo;

/**
 * Solicitud de tablet que queda en espera cuando no hay tablets disponibles.
 */
public class Solicitud {

    // Datos del usuario que solicita la tablet
    private String cedula;
    private String nombre;
    private String tipoUsuario;

    // Inicializa los datos de la solicitud
    public Solicitud(String cedula, String nombre, String tipoUsuario) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.tipoUsuario = tipoUsuario;
    }

    // Obtiene la cédula del usuario
    public String getCedula() {
        return cedula;
    }

    // Obtiene el nombre del usuario
    public String getNombre() {
        return nombre;
    }

    // Obtiene el tipo de usuario
    public String getTipoUsuario() {
        return tipoUsuario;
    }

    @Override
    public String toString() {
        return String.format("Cedula: %s | %-20s | %s", cedula, nombre, tipoUsuario);
    }
}