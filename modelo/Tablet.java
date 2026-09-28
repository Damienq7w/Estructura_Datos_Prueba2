package modelo;

/**
 * Representa una tablet perteneciente al inventario
 * de la biblioteca digital.
 * Responsable: Chalco Tasna Kenneth Mateo
 */
public class Tablet {

    // Estados permitidos para una tablet dentro del sistema.
    // Se usan constantes para evitar escribir los estados de distintas formas.
    public static final String DISPONIBLE = "DISPONIBLE";
    public static final String PRESTADA = "PRESTADA";
    public static final String MANTENIMIENTO = "MANTENIMIENTO";

    // Informacion principal que identifica y describe una tablet.
    private String codigo;
    private String marca;
    private int almacenamientoGB;
    private String versionSistema;
    private String estado;

    /**
     * Constructor utilizado para crear una nueva tablet
     * con todos sus datos principales.
     */
    public Tablet(String codigo, String marca, int almacenamientoGB, String versionSistema, String estado) {
        this.codigo = codigo;
        this.marca = marca;
        this.almacenamientoGB = almacenamientoGB;
        this.versionSistema = versionSistema;
        this.estado = estado;
    }

    /**
     * Verifica que el estado recibido sea uno de los
     * tres estados permitidos dentro del sistema.
     */
    public static boolean esEstadoValido(String estado) {
        return DISPONIBLE.equals(estado) || PRESTADA.equals(estado) || MANTENIMIENTO.equals(estado);
    }

    // Devuelve el codigo unico de la tablet.
    public String getCodigo() {
        return codigo;
    }

    // Devuelve la marca de la tablet.
    public String getMarca() {
        return marca;
    }

    // Devuelve la capacidad de almacenamiento expresada en GB.
    public int getAlmacenamientoGB() {
        return almacenamientoGB;
    }

    // Devuelve la version del sistema operativo.
    public String getVersionSistema() {
        return versionSistema;
    }

    // Devuelve el estado actual de la tablet.
    public String getEstado() {
        return estado;
    }

    /**
     * Permite modificar el estado de una tablet.
     * Se utiliza principalmente durante prestamos,
     * devoluciones y mantenimiento.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Devuelve los datos de la tablet en una sola linea
     * y con formato ordenado para mostrarlos en consola.
     */
    @Override
    public String toString() {
        return String.format("%-7s | %-9s | %4d GB | %-12s | %s",
                codigo, marca, almacenamientoGB, versionSistema, estado);
    }
}