package modelo;

public class Tablet {
    private String codigo;
    private String marca;
    private int almacenamientoGB;
    private String versionSistema;
    private String estado;

    public Tablet(String codigo, String marca, int almacenamientoGB, String versionSistema, String estado) {
        this.codigo = codigo.trim().toUpperCase();
        this.marca = marca.trim();
        this.almacenamientoGB = almacenamientoGB;
        this.versionSistema = versionSistema.trim();
        setEstado(estado);
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
        if (estado == null) {
            this.estado = "";
            return;
        }
        this.estado = estado.trim().toUpperCase();
    }

    @Override
    public String toString() {
        return codigo + " | " + marca + " | " + almacenamientoGB + " GB | " + versionSistema + " | " + estado;
    }
}