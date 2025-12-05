/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.pucp.transitsoft.bo;

import java.util.ArrayList;
import pe.edu.pucp.softinv.daoImp.InfraccionDAOImpl;
import pe.pucp.transitsoft.dao.InfraccionDAO;
import pe.pucp.transitsoft.model.InfraccionDTO;

public class InfraccionBO {
    InfraccionDAO infraccionDAO;
    
    public InfraccionBO() {
        this.infraccionDAO = new InfraccionDAOImpl();
    }
    
    public int insertar(InfraccionDTO infraccion) {
        return this.infraccionDAO.insertar(infraccion);
    }
    
    public ArrayList<InfraccionDTO> listarTodos() {
        return this.infraccionDAO.listarTodos();
    }
}