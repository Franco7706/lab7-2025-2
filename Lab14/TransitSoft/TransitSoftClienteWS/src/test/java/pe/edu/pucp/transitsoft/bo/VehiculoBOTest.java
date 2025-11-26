/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pe.edu.pucp.transitsoft.bo;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import pe.pucp.transitsoft.model.VehiculoDTO;

/**
 *
 * @author Usuario
 */
public class VehiculoBOTest {
    
    private VehiculoBO vehiculoBO;
    
    public VehiculoBOTest() {
        vehiculoBO = new VehiculoBO();
    }

    @org.junit.jupiter.api.Test
    public void testListarPorPropietario() throws Exception {
       ArrayList<VehiculoDTO> vehiculos = vehiculoBO.listarPorPropietario("Mónica");
        assertNotNull(vehiculos);
        for(VehiculoDTO v : vehiculos){
            System.out.println(v.getId() + " " + v.getPlaca());
        }
    }
    
}
