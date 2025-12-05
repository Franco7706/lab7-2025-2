/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.pucp.transitsoft.bo;

import java.util.ArrayList;
import pe.edu.pucp.softinv.daoImp.CamaraDAOImpl;
import pe.pucp.transitsoft.dao.CamaraDAO;
import pe.pucp.transitsoft.model.CamaraDTO;

/**
 *
 * @author alulab14
 */
public class CamaraBO {
    CamaraDAO camaraDAO;
    
    public CamaraBO() {
        this.camaraDAO = new CamaraDAOImpl();
    }
    
    public int insertar(CamaraDTO camara) {
        return this.camaraDAO.insertar(camara);
    }

    public int modificar(CamaraDTO camara) {
        return this.camaraDAO.modificar(camara);
    }

    public int eliminar(Integer id) {
        CamaraDTO camara = new CamaraDTO();
        camara.setId(id);
        return this.camaraDAO.eliminar(camara);
    }

    public CamaraDTO obtenerPorId(Integer id) {
        return this.camaraDAO.obtenerPorId(id);
    }

    public ArrayList<CamaraDTO> listarTodos() {
        return this.camaraDAO.listarTodos();
    }
}