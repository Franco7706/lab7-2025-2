package pe.edu.pucp.softex2.model;

public class InfraccionesDTO {

    private Integer infraccionId;
    private String descripcion;
    private Double montoMulta;
    private GravedadDTO gravedad;
    private Integer puntos;

    public InfraccionesDTO() {
        this.infraccionId = null;
        this.descripcion = null;
        this.montoMulta = null;
        this.gravedad = null;
        this.puntos = null;
    }
    
    public InfraccionesDTO(Integer infraccionId, String descripcion, Double montoMulta, GravedadDTO gravedad, Integer puntos) {
        this.infraccionId = infraccionId;
        this.descripcion = descripcion;
        this.montoMulta = montoMulta;
        this.gravedad = gravedad;
        this.puntos = puntos;
    }

    /**
     * @return the infraccionId
     */
    public Integer getInfraccionId() {
        return infraccionId;
    }

    /**
     * @param infraccionId the infraccionId to set
     */
    public void setInfraccionId(Integer infraccionId) {
        this.infraccionId = infraccionId;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the montoMulta
     */
    public Double getMontoMulta() {
        return montoMulta;
    }

    /**
     * @param montoMulta the montoMulta to set
     */
    public void setMontoMulta(Double montoMulta) {
        this.montoMulta = montoMulta;
    }

    /**
     * @return the gravedad
     */
    public GravedadDTO getGravedad() {
        return gravedad;
    }

    /**
     * @param gravedad the gravedad to set
     */
    public void setGravedad(GravedadDTO gravedad) {
        this.gravedad = gravedad;
    }

    /**
     * @return the puntos
     */
    public Integer getPuntos() {
        return puntos;
    }

    /**
     * @param puntos the puntos to set
     */
    public void setPuntos(Integer puntos) {
        this.puntos = puntos;
    }

}
