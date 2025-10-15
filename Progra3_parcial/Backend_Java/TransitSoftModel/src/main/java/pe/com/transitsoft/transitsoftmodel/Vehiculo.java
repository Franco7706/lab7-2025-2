package pe.com.transitsoft.transitsoftmodel;

public class Vehiculo {
    private int vehiculoId;
    private String placa;
    private String marca;
    private String modelo;
    private int anho;

    public Vehiculo(){
        vehiculoId = 0;
        placa = null;
        marca = null;
        modelo = null;
        anho = 0;
    }
    
    public Vehiculo(int vehiculoId, String placa, String marca, String modelo, int anho){
        this.vehiculoId = vehiculoId;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.anho = anho;
    }
    public int getVehiculoId() {
        return vehiculoId;
    }

    public void setVehiculoId(int vehiculoId) {
        this.vehiculoId = vehiculoId;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAnho() {
        return anho;
    }

    public void setAnho(int anho) {
        this.anho = anho;
    }
    
}
