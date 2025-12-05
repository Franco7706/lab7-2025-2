package pe.com.transitsoft.modelo;

/**
 *
 * @author eric
 */
public class Conductor {
    private int conductorId;
    private String paterno;
    private String materno;
    private String nombres;
    private String numLicencia;
    private String tipoLicenciaId;
    private int puntosAcumulados;
    
    public int getConductorId() {
        return conductorId;
    }

    public void setConductorId(int conductorId) {
        this.conductorId = conductorId;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getNumLicencia() {
        return numLicencia;
    }

    public void setNumLicencia(String numLicencia) {
        this.numLicencia = numLicencia;
    }

    public String getTipoLicenciaId() {
        return tipoLicenciaId;
    }

    public void setTipoLicenciaId(String tipoLicenciaId) {
        this.tipoLicenciaId = tipoLicenciaId;
    }

    public int getPuntosAcumulados() {
        return puntosAcumulados;
    }

    public void setPuntosAcumulados(int puntosAcumulados) {
        this.puntosAcumulados = puntosAcumulados;
    }
}
