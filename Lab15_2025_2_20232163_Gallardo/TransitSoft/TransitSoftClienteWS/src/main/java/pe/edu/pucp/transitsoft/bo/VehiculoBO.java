package pe.edu.pucp.transitsoft.bo;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.ws.rs.core.Response;
import java.io.IOException;
import pe.edu.pucp.transitsoft.model.PropietarioDTO;
import pe.edu.pucp.transitsoft.model.VehiculoDTO;

public class VehiculoBO extends BOBase {
    
    public VehiculoBO(){
        super("urlvehiculos");
    }
    
    public VehiculoDTO insertarOModificar(VehiculoDTO vehiculoDTO, String motor) throws JsonProcessingException, IOException, InterruptedException{        
        this.crearHttpClient();
        this.crearHttpRequestPOSTConUrlCustom(serializarDTO(vehiculoDTO),this.url+"?motor="+motor);
        this.enviarRequest();
        VehiculoDTO vehiculo;
        if(response.statusCode()==Response.Status.CREATED.getStatusCode() || response.statusCode()==Response.Status.OK.getStatusCode()){
            vehiculo = (VehiculoDTO) this.deserializarDTO(VehiculoDTO.class);
        }
        else{
            vehiculo = new VehiculoDTO();
        }
        this.cerrarHttpClient();
        return vehiculo;
    }

    private VehiculoDTO crearDTO(Integer id, String placa, String marca, String modelo, Integer anho, PropietarioDTO propietario) {
        VehiculoDTO vehiculoDTO = new VehiculoDTO();
        vehiculoDTO.setId(id);
        vehiculoDTO.setPlaca(placa);
        vehiculoDTO.setMarca(marca);
        vehiculoDTO.setModelo(modelo);
        vehiculoDTO.setAnho(anho);
        vehiculoDTO.setPropietario(propietario);
        return vehiculoDTO;
    }
}
