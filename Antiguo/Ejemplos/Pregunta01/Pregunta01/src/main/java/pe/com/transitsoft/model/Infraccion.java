package pe.com.transitsoft.model;

/**
 *
 * @author eric
 */
public class Infraccion {
    private int infraccionId;
    private String descripcion;
    private double montoMulta;
    private Gravedad gravedad;
    private int puntos;
    
    public int getInfraccionId() {
        return infraccionId;
    }

    public void setInfraccionId(int infraccionId) {
        this.infraccionId = infraccionId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getMontoMulta() {
        return montoMulta;
    }

    public void setMontoMulta(double montoMulta) {
        this.montoMulta = montoMulta;
    }

    public Gravedad getGravedad() {
        return gravedad;
    }

    public void setGravedad(Gravedad gravedad) {
        this.gravedad = gravedad;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
}
