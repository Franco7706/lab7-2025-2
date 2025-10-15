package pe.com.transitsoft.model;

import java.util.Date;

/**
 *
 * @author eric
 */
public class RegistroInfraccion {
    private Date fecha;
    private Vehiculo vehiculo;
    private Conductor conductor;
    private Infraccion infraccion;

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the vehiculo
     */
    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    /**
     * @param vehiculo the vehiculo to set
     */
    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    /**
     * @return the conductor
     */
    public Conductor getConductor() {
        return conductor;
    }

    /**
     * @param conductor the conductor to set
     */
    public void setConductor(Conductor conductor) {
        this.conductor = conductor;
    }

    /**
     * @return the infraccion
     */
    public Infraccion getInfraccion() {
        return infraccion;
    }

    /**
     * @param infraccion the infraccion to set
     */
    public void setInfraccion(Infraccion infraccion) {
        this.infraccion = infraccion;
    }
    
}