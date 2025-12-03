package pe.edu.pucp.prog3.ex2.concurrente;

import java.time.LocalDateTime;


public class Captura {
    private final String placa;
    private final double velocidad;
    private final String fecha;
    private final String hora;

    public Captura(String placa, double velocidad, LocalDateTime now) {
        this.placa = placa;
        this.velocidad = velocidad;
        this.fecha = String.format("%d-%d-%d", now.getDayOfMonth(), now.getMonthValue(), now.getYear());
        this.hora = String.format("%d:%d:%d", now.getHour(), now.getMinute(), now.getSecond());
    }

    public double getVelocidad() {
        return velocidad;
    }

    @Override
    public String toString() {
        return String.format("Placa: %s, Velocidad: %.2f, Fecha: %s, Hora: %s", placa, velocidad, fecha, hora);
    }
}
