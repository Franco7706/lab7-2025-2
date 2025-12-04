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
        VehiculoDTO vehiculoDTO = new VehiculoDTO(0,"ABC-223","Kia","Rio",2021,propietarioDTO);
        String motor = "MySQL";
        VehiculoDTO result = vehiculoBO.insertarOModificar(vehiculoDTO, motor);
        assertNotEquals(result.getId(), 0);
        System.out.println(result.getPlaca());
        propietarioDTO = new PropietarioDTO();
        propietarioDTO.setId(1);
        vehiculoDTO = new VehiculoDTO(0,"ABC-223","Kia","Rio",2021,propietarioDTO);
        motor = "MSSQL";
        result = vehiculoBO.insertarOModificar(vehiculoDTO, motor);
        assertNotEquals(result.getId(), 0);
        System.out.println(result.getPlaca());
    }
    
}
