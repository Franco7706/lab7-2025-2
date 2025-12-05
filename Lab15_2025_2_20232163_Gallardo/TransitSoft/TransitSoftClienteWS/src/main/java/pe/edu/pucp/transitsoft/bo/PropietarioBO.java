package pe.edu.pucp.transitsoft.bo;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.ws.rs.core.Response;
import java.io.IOException;
import pe.edu.pucp.transitsoft.model.PropietarioDTO;

public class PropietarioBO extends BOBase {
    
    public PropietarioBO(){
        super("urlpropietarios");
    }
    
    public PropietarioDTO obtenerPropietarioVehiculo(Integer vehiculoId, String motor) throws JsonProcessingException, IOException, InterruptedException{        
        this.crearHttpClient();
        this.crearHttpRequestGETConQueryParam(vehiculoId,"?motor="+motor);
        this.enviarRequest();
        PropietarioDTO propietario = (PropietarioDTO) this.deserializarDTO(PropietarioDTO.class);                
        this.cerrarHttpClient();
        if (response.statusCode() != Response.Status.NOT_FOUND.getStatusCode())
            return propietario;
        return new PropietarioDTO();                
    }
    
    private PropietarioDTO crearDTO(Integer id, String dni, String nombres, String apellidos, String direccion) {
        PropietarioDTO propietarioDTO = new PropietarioDTO();
        propietarioDTO.setId(id);
        propietarioDTO.setDni(dni);
        propietarioDTO.setNombres(nombres);
        propietarioDTO.setApellidos(apellidos);
        propietarioDTO.setDireccion(direccion);
        
        return propietarioDTO;
    }
}
