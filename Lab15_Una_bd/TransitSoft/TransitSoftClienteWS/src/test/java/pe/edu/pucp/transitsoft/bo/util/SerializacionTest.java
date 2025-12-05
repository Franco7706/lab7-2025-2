package pe.edu.pucp.transitsoft.bo.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import pe.edu.pucp.transitsoft.model.PropietarioDTO;
import pe.edu.pucp.transitsoft.model.VehiculoDTO;

public class SerializacionTest {
    private ObjectMapper objectMapper;
    
    public SerializacionTest() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testSerializacionVehiculo() throws Exception {
        System.out.println("obtenerPropietarioVehiculo");
        PropietarioDTO propietarioDTO = new PropietarioDTO();
        propietarioDTO.setId(1);
        VehiculoDTO vehiculoDTO = new VehiculoDTO(0,"ABC-223","Kia","Rio",2021,propietarioDTO);
        String jsonRequest = objectMapper.writeValueAsString(vehiculoDTO);

        System.out.println(jsonRequest);
    }
    
}
