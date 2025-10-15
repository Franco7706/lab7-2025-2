package pe.com.transitsoft.transitsoftmodel;


public class VehiculoPropietario {
    private int vehiculoPropietarioId;
    private Vehiculo vehiculo;
    private Propietario propietario;

    public VehiculoPropietario(){
        vehiculoPropietarioId=0;
        vehiculo=null;
        propietario=null;
    }
    
    public VehiculoPropietario(int vehiculoPropietarioId, Vehiculo vehiculo, Propietario propietario){
        this.vehiculoPropietarioId=vehiculoPropietarioId;
        this.vehiculo=vehiculo;
        this.propietario=propietario;
    }
    
    public int getVehiculoPropietarioId() {
        return vehiculoPropietarioId;
    }

    public void setVehiculoPropietarioId(int vehiculoPropietarioId) {
        this.vehiculoPropietarioId = vehiculoPropietarioId;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }
    
    
}
