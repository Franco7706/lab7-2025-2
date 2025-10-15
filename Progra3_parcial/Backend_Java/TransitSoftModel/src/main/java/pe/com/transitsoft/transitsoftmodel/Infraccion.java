package pe.com.transitsoft.transitsoftmodel;

import java.util.Date;

public class Infraccion {
    private int infraccionId;
    private String placa;
    private Double velocidad;
    private Double limite;
    private Double exceso;
    private String vehiculoMarca;
    private String vehiculoModelo;
    private int vehiculoAnho;
    private String propietarioDni;
    private String propietarioNombres;
    private String propietarioApellidos;
    private String propietarioDireccion;
    private int camaraId;
    private String camaraModelo;
    private String camaraCodigoSerie;
    private int camaraLatitud;
    private int camaraLongitud;
    private Double monto;
    private Date fechaCaptura;
    private Date fechaRegistro;

    public Infraccion() {
        this.infraccionId = 0;
        this.placa = null;
        this.velocidad = null;
        this.limite = null;
        this.exceso = null;
        this.vehiculoMarca = null;
        this.vehiculoModelo = null;
        this.vehiculoAnho = 0;
        this.propietarioDni = null;
        this.propietarioNombres = null;
        this.propietarioApellidos = null;
        this.propietarioDireccion = null;
        this.camaraId = 0;
        this.camaraModelo = null;
        this.camaraCodigoSerie = null;
        this.camaraLatitud = 0;
        this.camaraLongitud = 0;
        this.monto = null;
        this.fechaCaptura = null;
        this.fechaRegistro = null;
    }

    public Infraccion(int infraccionId, String placa, Double velocidad, Double limite, Double exceso, String vehiculoMarca, String vehiculoModelo, int vehiculoAnho, String propietarioDni, String propietarioNombres, String propietarioApellidos, String propietarioDireccion, int camaraId, String camaraModelo, String camaraCodigoSerie, int camaraLatitud, int camaraLongitud, Double monto, Date fechaCaptura, Date fechaRegistro) {
        this.infraccionId = infraccionId;
        this.placa = placa;
        this.velocidad = velocidad;
        this.limite = limite;
        this.exceso = exceso;
        this.vehiculoMarca = vehiculoMarca;
        this.vehiculoModelo = vehiculoModelo;
        this.vehiculoAnho = vehiculoAnho;
        this.propietarioDni = propietarioDni;
        this.propietarioNombres = propietarioNombres;
        this.propietarioApellidos = propietarioApellidos;
        this.propietarioDireccion = propietarioDireccion;
        this.camaraId = camaraId;
        this.camaraModelo = camaraModelo;
        this.camaraCodigoSerie = camaraCodigoSerie;
        this.camaraLatitud = camaraLatitud;
        this.camaraLongitud = camaraLongitud;
        this.monto = monto;
        this.fechaCaptura = fechaCaptura;
        this.fechaRegistro = fechaRegistro;
    }

    
    
    public int getInfraccionId() {
        return infraccionId;
    }

    public void setInfraccionId(int infraccionId) {
        this.infraccionId = infraccionId;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Double getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(Double velocidad) {
        this.velocidad = velocidad;
    }

    public Double getLimite() {
        return limite;
    }

    public void setLimite(Double limite) {
        this.limite = limite;
    }

    public Double getExceso() {
        return exceso;
    }

    public void setExceso(Double exceso) {
        this.exceso = exceso;
    }

    public String getVehiculoMarca() {
        return vehiculoMarca;
    }

    public void setVehiculoMarca(String vehiculoMarca) {
        this.vehiculoMarca = vehiculoMarca;
    }

    public String getVehiculoModelo() {
        return vehiculoModelo;
    }

    public void setVehiculoModelo(String vehiculoModelo) {
        this.vehiculoModelo = vehiculoModelo;
    }

    public int getVehiculoAnho() {
        return vehiculoAnho;
    }

    public void setVehiculoAnho(int vehiculoAnho) {
        this.vehiculoAnho = vehiculoAnho;
    }

    public String getPropietarioDni() {
        return propietarioDni;
    }
    
    public void setPropietarioDni(String propietarioDni) {
        this.propietarioDni = propietarioDni;
    }

    public String getPropietarioNombres() {
        return propietarioNombres;
    }

    public void setPropietarioNombres(String propietarioNombres) {
        this.propietarioNombres = propietarioNombres;
    }

    public String getPropietarioApellidos() {
        return propietarioApellidos;
    }
    
    public void setPropietarioApellidos(String propietarioApellidos) {
        this.propietarioApellidos = propietarioApellidos;
    }

    public String getPropietarioDireccion() {
        return propietarioDireccion;
    }

    public void setPropietarioDireccion(String propietarioDireccion) {
        this.propietarioDireccion = propietarioDireccion;
    }

    public int getCamaraId() {
        return camaraId;
    }

    public void setCamaraId(int camaraId) {
        this.camaraId = camaraId;
    }

    public String getCamaraModelo() {
        return camaraModelo;
    }

    public void setCamaraModelo(String camaraModelo) {
        this.camaraModelo = camaraModelo;
    }

    public String getCamaraCodigoSerie() {
        return camaraCodigoSerie;
    }

    public void setCamaraCodigoSerie(String camaraCodigoSerie) {
        this.camaraCodigoSerie = camaraCodigoSerie;
    }

    public int getCamaraLatitud() {
        return camaraLatitud;
    }

    public void setCamaraLatitud(int camaraLatitud) {
        this.camaraLatitud = camaraLatitud;
    }

    public int getCamaraLongitud() {
        return camaraLongitud;
    }

    public void setCamaraLongitud(int camaraLongitud) {
        this.camaraLongitud = camaraLongitud;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Date getFechaCaptura() {
        return fechaCaptura;
    }

    public void setFechaCaptura(Date fechaCaptura) {
        this.fechaCaptura = fechaCaptura;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
    
}
