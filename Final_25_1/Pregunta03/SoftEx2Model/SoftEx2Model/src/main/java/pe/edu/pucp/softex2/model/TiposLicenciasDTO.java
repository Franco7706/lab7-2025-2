package pe.edu.pucp.softex2.model;


public class TiposLicenciasDTO {
    private Integer tipoLicenciaId;
    private String nombre;
    private String descripcion;

    public TiposLicenciasDTO() {
        this.tipoLicenciaId = null;
        this.nombre = null;
        this.descripcion = null;
    }
    
    public TiposLicenciasDTO(Integer tipoLicenciaId, String nombre, String descripcion) {
        this.tipoLicenciaId = tipoLicenciaId;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    /**
     * @return the tipoLicenciaId
     */
    public Integer getTipoLicenciaId() {
        return tipoLicenciaId;
    }

    /**
     * @param tipoLicenciaId the tipoLicenciaId to set
     */
    public void setTipoLicenciaId(Integer tipoLicenciaId) {
        this.tipoLicenciaId = tipoLicenciaId;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
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
    
    
}
