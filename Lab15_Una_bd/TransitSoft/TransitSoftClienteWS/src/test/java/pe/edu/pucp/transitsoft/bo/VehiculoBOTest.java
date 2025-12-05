package pe.edu.pucp.transitsoft.bo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import pe.edu.pucp.transitsoft.model.PropietarioDTO;
import pe.edu.pucp.transitsoft.model.VehiculoDTO;

public class VehiculoBOTest {
    
    private VehiculoBO vehiculoBO;
    
    public VehiculoBOTest() {
        vehiculoBO = new VehiculoBO();
    }

    @Test
    public void testInsertarOModificar() throws Exception { // el propietario ya debe existir
        System.out.println("insertarOModificar");
        PropietarioDTO propietarioDTO = new PropietarioDTO();
        propietarioDTO.setId(1);
        VehiculoDTO vehiculoDTO = new VehiculoDTO(0,"ZZZ-223","Kia","Rio",2021,propietarioDTO);
        VehiculoDTO result = vehiculoBO.insertarOModificar(vehiculoDTO);
        assertNotEquals(result.getId(), 0);
        System.out.println(result.getPlaca());
        propietarioDTO = new PropietarioDTO();
        propietarioDTO.setId(1);
        vehiculoDTO = new VehiculoDTO(0,"ZZZ-223","Kia","Rio",2021,propietarioDTO);
        result = vehiculoBO.insertarOModificar(vehiculoDTO);
        assertNotEquals(result.getId(), 0);
        System.out.println(result.getPlaca());
    }
    
}
