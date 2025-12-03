package pe.edu.pucp.transitsoft.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import pe.edu.pucp.transitsoft.bo.ConductorBO;
import pe.edu.pucp.transitsoft.model.Conductor;
import pe.edu.pucp.transitsoft.model.VehiculoConductor;

@WebService(serviceName = "ConductorWS")
public class ConductorWS {

    private ConductorBO boConductor;

    public ConductorWS() {
        boConductor = new ConductorBO();
    }
    
    @WebMethod(operationName = "insertarConductor")
    public int insertarConductor(@WebParam(name = "conductor") Conductor conductor,
            @WebParam(name = "vehiculos") ArrayList<VehiculoConductor> vehiculosConductor){
        return boConductor.insertar(conductor,vehiculosConductor);
    }
}
