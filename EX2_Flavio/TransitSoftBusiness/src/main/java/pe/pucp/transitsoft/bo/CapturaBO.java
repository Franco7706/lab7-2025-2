/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.pucp.transitsoft.bo;

import java.util.ArrayList;
import pe.edu.pucp.softinv.daoImp.CapturaDAOImpl;
import pe.pucp.transitsoft.dao.CapturaDAO;
import pe.pucp.transitsoft.model.CapturaDTO;

/**
 *
 * @author alulab14
 */
public class CapturaBO {
    CapturaDAO capturaDAO;
    
    public CapturaBO() {
        this.capturaDAO = new CapturaDAOImpl();
    }
    
    public int insertar(CapturaDTO captura) {
        return this.capturaDAO.insertar(captura);
    }
    
    public int modificar(CapturaDTO captura) {
        return this.capturaDAO.modificar(captura);
    }
    
    public int eliminar(Integer id) {
        CapturaDTO captura = new CapturaDTO();
        captura.setId(id);
        return this.capturaDAO.eliminar(captura);
    }
    
    public CapturaDTO obtenerPorId(Integer id) {
        return this.capturaDAO.obtenerPorId(id);
    }
    
    public ArrayList<CapturaDTO> listarTodos() {
        return this.capturaDAO.listarTodos();
    }
}