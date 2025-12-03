package pe.edu.pucp.prog3.ex2.concurrente;


public class Vehiculo {
    private String placa;
    private double velocidad;

    public Vehiculo(String placa, double velocidad) {
        this.placa = placa;
        this.velocidad = velocidad;
    }
    
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public double getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(double velocidad) {
        this.velocidad = velocidad;
    }
}
