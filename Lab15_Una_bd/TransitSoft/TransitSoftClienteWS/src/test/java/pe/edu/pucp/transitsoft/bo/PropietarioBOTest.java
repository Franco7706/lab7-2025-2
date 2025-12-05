package pe.edu.pucp.transitsoft.bo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import pe.edu.pucp.transitsoft.model.PropietarioDTO;

public class PropietarioBOTest {
    
    private PropietarioBO propietarioBO;
    
    public PropietarioBOTest() {
        propietarioBO = new PropietarioBO();
    }

    @Test
    public void testObtenerPropietarioVehiculo() throws Exception {
        System.out.println("obtenerPropietarioVehiculo");
        Integer vehiculoId = 1;
        PropietarioDTO result = propietarioBO.obtenerPropietarioVehiculo(vehiculoId);
        assertNotEquals(result.getId(),0);
        System.out.println(result.getNombres() + " " + result.getApellidos());
    }
    
}
