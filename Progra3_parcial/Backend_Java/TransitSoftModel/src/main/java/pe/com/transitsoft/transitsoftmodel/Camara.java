package pe.com.transitsoft.transitsoftmodel;

public class Camara {
    private int camaraId;
    private String modelo;
    private String codigoSerie;
    private int latitud;
    private int longitud;

    public Camara() {
        camaraId = 0;
        modelo = null;
        codigoSerie = null;
        latitud = 0;
        longitud = 0;
    }

    public Camara(int camaraId, String modelo, String codigoSerie, int latitud, int longitud) {
        this.camaraId = camaraId;
        this.modelo = modelo;
        this.codigoSerie = codigoSerie;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    
    
    public int getCamaraId() {
        return camaraId;
    }

    public void setCamaraId(int camaraId) {
        this.camaraId = camaraId;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCodigoSerie() {
        return codigoSerie;
    }

    public void setCodigoSerie(String codigoSerie) {
        this.codigoSerie = codigoSerie;
    }

    public int getLatitud() {
        return latitud;
    }

    public void setLatitud(int latitud) {
        this.latitud = latitud;
    }

    public int getLongitud() {
        return longitud;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }
    
}
