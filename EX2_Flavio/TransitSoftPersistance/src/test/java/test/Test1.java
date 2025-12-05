/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import pe.edu.pucp.softinv.daoImp.VehiculoDAOImpl;
import pe.pucp.transitsoft.dao.VehiculoDAO;
import pe.pucp.transitsoft.model.VehiculoDTO;

/**
 *
 * @author alulab14
 */
public class Test1 {
    private VehiculoDAO vehiculoDAO;
    public Test1(){
        vehiculoDAO = new VehiculoDAOImpl();
    }
    
    @Test
    public void test() {
        //ArrayList<VehiculoDTO> vehiculos = vehiculoDAO.listarTodos();
        //System.out.println(vehiculos.get(0).getModelo());
    }
}
