package pe.edu.pucp.softex2.model;

public class ConductoresDTO {

    private Integer conductorId;
    private String paterno;
    private String materno;
    private String nombres;
    private String numLicencia;
    private TiposLicenciasDTO tipoLicencia;
    private Integer puntosAcumulados;

    public ConductoresDTO() {
        this.conductorId = null;
        this.paterno = null;
        this.materno = null;
        this.nombres = null;
        this.numLicencia = null;
        this.tipoLicencia = null;
        this.puntosAcumulados = null;
    }
    
    public ConductoresDTO(Integer conductorId, String paterno, String materno, String nombres, String numLicencia, Integer tipoLicenciaId, Integer puntosAcumulados) {
        this.conductorId = conductorId;
        this.paterno = paterno;
        this.materno = materno;
        this.nombres = nombres;
        this.numLicencia = numLicencia;
        this.tipoLicencia = new TiposLicenciasDTO();
        this.tipoLicencia.setTipoLicenciaId(tipoLicenciaId);        
        this.puntosAcumulados = puntosAcumulados;
    }

    @Override
    public String toString(){
        String cadena = "";
        cadena += this.conductorId;
        cadena += ",";
        cadena += this.paterno;
        cadena += ",";
        cadena += this.materno;
        cadena += ",";
        cadena += this.nombres;
        cadena += ",";
        cadena += this.numLicencia;
        cadena += ",";
        
        cadena += this.tipoLicencia.getTipoLicenciaId();
        cadena += ",";
        cadena += this.tipoLicencia.getNombre();
        cadena += ",";
        cadena += this.tipoLicencia.getDescripcion();
        cadena += ",";
        
        cadena += this.puntosAcumulados;
        cadena += ";";
        return cadena;
    }
    
    /**
     * @return the conductorId
     */
    public Integer getConductorId() {
        return conductorId;
    }

    /**
     * @param conductorId the conductorId to set
     */
    public void setConductorId(Integer conductorId) {
        this.conductorId = conductorId;
    }

    /**
     * @return the paterno
     */
    public String getPaterno() {
        return paterno;
    }

    /**
     * @param paterno the paterno to set
     */
    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    /**
     * @return the materno
     */
    public String getMaterno() {
        return materno;
    }

    /**
     * @param materno the materno to set
     */
    public void setMaterno(String materno) {
        this.materno = materno;
    }

    /**
     * @return the nombres
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * @param nombres the nombres to set
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * @return the numLicencia
     */
    public String getNumLicencia() {
        return numLicencia;
    }

    /**
     * @param numLicencia the numLicencia to set
     */
    public void setNumLicencia(String numLicencia) {
        this.numLicencia = numLicencia;
    }

    /**
     * @return the puntosAcumulados
     */
    public Integer getPuntosAcumulados() {
        return puntosAcumulados;
    }

    /**
     * @param puntosAcumulados the puntosAcumulados to set
     */
    public void setPuntosAcumulados(Integer puntosAcumulados) {
        this.puntosAcumulados = puntosAcumulados;
    }

    /**
     * @return the tipoLicencia
     */
    public TiposLicenciasDTO getTipoLicencia() {
        return tipoLicencia;
    }

    /**
     * @param tipoLicencia the tipoLicencia to set
     */
    public void setTipoLicencia(TiposLicenciasDTO tipoLicencia) {
        this.tipoLicencia = tipoLicencia;
    }

}
