package pe.edu.pucp.transitsoft.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import pe.edu.pucp.transitsoft.bo.VehiculoBO;
import pe.edu.pucp.transitsoft.model.Vehiculo;

@WebService(serviceName = "VehiculoWS")
public class VehiculoWS {

    private VehiculoBO boVehiculo;
    
    public VehiculoWS(){
        boVehiculo = new VehiculoBO();
    }
    
    @WebMethod(operationName = "listarVehiculosPorPlaca")
    public ArrayList<Vehiculo> listarVehiculosPorPlaca(@WebParam(name = "placa") String placa) {
        return boVehiculo.listarPorPlaca(placa);
    }
}
