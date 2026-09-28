package modelo;

/**
 * Tablet de la biblioteca digital.
 * Responsable: Chalco Tasna Kenneth Mateo
 */
public class Tablet {

    public static final String DISPONIBLE = "DISPONIBLE";
    public static final String PRESTADA = "PRESTADA";
    public static final String MANTENIMIENTO = "MANTENIMIENTO";

    private String codigo;
    private String marca;
    private int almacenamientoGB;
    private String versionSistema;
    private String estado;

    public Tablet(String codigo, String marca, int almacenamientoGB, String versionSistema, String estado) {
        this.codigo = codigo;
        this.marca = marca;
        this.almacenamientoGB = almacenamientoGB;
        this.versionSistema = versionSistema;
        this.estado = estado;
    }

    public static boolean esEstadoValido(String estado) {
        return DISPONIBLE.equals(estado) || PRESTADA.equals(estado) || MANTENIMIENTO.equals(estado);
    }

    public String getCodigo() {
        return codigo;
    }

    public String getMarca() {
        return marca;
    }

    public int getAlmacenamientoGB() {
        return almacenamientoGB;
    }

    public String getVersionSistema() {
        return versionSistema;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return String.format("%-7s | %-9s | %4d GB | %-12s | %s",
                codigo, marca, almacenamientoGB, versionSistema, estado);
    }
}
