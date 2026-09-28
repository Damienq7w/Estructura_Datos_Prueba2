package modelo;

/**
 * Prestamo activo de una tablet asociado a un numero de cedula.
 * Responsable: Silva Camuendo Luis Alexander
 */
public class Prestamo {

    public static final String ESTUDIANTE = "ESTUDIANTE";
    public static final String GRUPO_INVESTIGACION = "GRUPO_INVESTIGACION";

    private String cedula;
    private String nombre;
    private String tipoUsuario;
    private String codigoTablet;

    public Prestamo(String cedula, String nombre, String tipoUsuario, String codigoTablet) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.tipoUsuario = tipoUsuario;
        this.codigoTablet = codigoTablet;
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

    public String getCodigoTablet() {
        return codigoTablet;
    }

    @Override
    public String toString() {
        return String.format("Cedula: %s | %-20s | %-19s | Tablet: %s",
                cedula, nombre, tipoUsuario, codigoTablet);
    }
}
