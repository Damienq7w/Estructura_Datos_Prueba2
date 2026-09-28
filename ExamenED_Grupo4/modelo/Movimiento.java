package modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Registro de una operacion importante del sistema (historial).
 * Responsable: Tacuri Santillan Monica Sara
 */
public class Movimiento {

    private static final DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    private String tipo;
    private String descripcion;
    private LocalDateTime fechaHora;

    public Movimiento(String tipo, String descripcion) {
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.fechaHora = LocalDateTime.now();
    }

    public String getTipo() {
        return tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    @Override
    public String toString() {
        return String.format("[%s] %-13s | %s", fechaHora.format(FORMATO), tipo, descripcion);
    }
}
