package pe.edu.pucp.transitsoft.model;

public class ConductoresDTO {
    private int idConductor;
    private int idTipoLicencia;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String numeroLicencia;
    private int puntosAcumulados;
    private VehiculosDTO vehiculo;//Para el POST

    public ConductoresDTO() {
    }

    public int getIdConductor() {
        return idConductor;
    }

    public void setIdConductor(int idConductor) {
        this.idConductor = idConductor;
    }

    public int getIdTipoLicencia() {
        return idTipoLicencia;
    }

    public void setIdTipoLicencia(int idTipoLicencia) {
        this.idTipoLicencia = idTipoLicencia;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getNumeroLicencia() {
        return numeroLicencia;
    }

    public void setNumeroLicencia(String numeroLicencia) {
        this.numeroLicencia = numeroLicencia;
    }

    public int getPuntosAcumulados() {
        return puntosAcumulados;
    }

    public void setPuntosAcumulados(int puntosAcumulados) {
        this.puntosAcumulados = puntosAcumulados;
    }

    public VehiculosDTO getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(VehiculosDTO vehiculo) {
        this.vehiculo = vehiculo;
    }
    
}
