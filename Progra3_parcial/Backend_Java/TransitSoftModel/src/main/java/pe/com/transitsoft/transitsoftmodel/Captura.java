package pe.com.transitsoft.transitsoftmodel;

import java.util.Date;

public class Captura {
    private int capturaId;
    private Camara camara;
    private String placa;
    private Double velocidad;
    private Date fechaCaptura;
    private String estado;

    public Captura() {
        capturaId = 0;
        camara = null;
        placa = null;
        velocidad = null;
        fechaCaptura = null;
        estado = null;
    }

    public Captura(int capturaId, Camara camara, String placa, Double velocidad, Date fechaCaptura, String estado) {
        this.capturaId = capturaId;
        this.camara = camara;
        this.placa = placa;
        this.velocidad = velocidad;
        this.fechaCaptura = fechaCaptura;
        this.estado = estado;
    }

    
    
    public int getCapturaId() {
        return capturaId;
    }

    public void setCapturaId(int capturaId) {
        this.capturaId = capturaId;
    }

    public Camara getCamara() {
        return camara;
    }

    public void setCamara(Camara camara) {
        this.camara = camara;
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

    public Date getFechaCaptura() {
        return fechaCaptura;
    }

    public void setFechaCaptura(Date fechaCaptura) {
        this.fechaCaptura = fechaCaptura;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
