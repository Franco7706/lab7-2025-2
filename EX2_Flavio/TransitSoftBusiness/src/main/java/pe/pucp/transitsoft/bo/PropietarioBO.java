/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.pucp.transitsoft.bo;

import java.util.ArrayList;
import pe.edu.pucp.softinv.daoImp.PropietarioDAOImpl;
import pe.pucp.transitsoft.dao.PropietarioDAO;
import pe.pucp.transitsoft.model.PropietarioDTO;

/**
 *
 * @author alulab14
 */
public class PropietarioBO {
    PropietarioDAO propietarioDAO;
    
    public PropietarioBO(){
        this.propietarioDAO = new PropietarioDAOImpl();
    }
    
    public int insertar(PropietarioDTO propietario){
        return this.propietarioDAO.insertar(propietario);
    }
    public int modificar(PropietarioDTO propietario){
        return this.propietarioDAO.modificar(propietario);
    }
    public int eliminar(Integer id) {
        PropietarioDTO propietario = new PropietarioDTO();
        propietario.setId(id);
        return this.propietarioDAO.eliminar(propietario);
    }
    public PropietarioDTO obtenerPorId(Integer id) {
        return this.propietarioDAO.obtenerPorId(id);
    }
    public ArrayList<PropietarioDTO> listarTodos(){
        return this.propietarioDAO.listarTodos();
    }
}
