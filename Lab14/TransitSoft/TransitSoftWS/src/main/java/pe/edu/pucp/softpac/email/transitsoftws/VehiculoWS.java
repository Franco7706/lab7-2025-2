package pe.edu.pucp.softpac.email.transitsoftws;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.io.IOException;
import java.util.ArrayList;
import pe.edu.pucp.transitsoft.bo.VehiculoBO;
import pe.pucp.transitsoft.model.VehiculoDTO;

@WebService(serviceName = "VehiculoWS")
public class VehiculoWS {

    private VehiculoBO vehiculoBO;
    
    public VehiculoWS(){
        vehiculoBO = new VehiculoBO();
    }
    
    @WebMethod(operationName = "ListarVehiculosPorPropietario")
    public ArrayList<VehiculoDTO> ListarVehiculosPorPropietario(@WebParam(name = "descripcion") String descripcion_propietario) throws IOException, JsonProcessingException, InterruptedException {
        return vehiculoBO.listarPorPropietario(descripcion_propietario);
    }
}
