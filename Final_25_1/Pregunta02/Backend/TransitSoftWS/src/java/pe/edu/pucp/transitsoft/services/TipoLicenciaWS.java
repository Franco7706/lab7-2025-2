package pe.edu.pucp.transitsoft.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import java.util.ArrayList;
import pe.edu.pucp.transitsoft.bo.TipoLicenciaBO;
import pe.edu.pucp.transitsoft.model.TipoLicencia;

@WebService(serviceName = "TipoLicenciaWS")
public class TipoLicenciaWS {

    private TipoLicenciaBO boTipoLicencia;

    public TipoLicenciaWS() {
        boTipoLicencia = new TipoLicenciaBO();
    }
    
    @WebMethod(operationName = "listarTiposLicencia")
    public ArrayList<TipoLicencia> listarTiposLicencia(){
        return boTipoLicencia.listarTodos();
    }
}
