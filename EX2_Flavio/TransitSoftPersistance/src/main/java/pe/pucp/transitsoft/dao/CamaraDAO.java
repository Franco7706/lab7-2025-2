/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.pucp.transitsoft.dao;

import java.util.ArrayList;
import pe.pucp.transitsoft.model.CamaraDTO;

/**
 *
 * @author Flavio
 */
public interface CamaraDAO {
    public int insertar(CamaraDTO camara);
    public int modificar(CamaraDTO camara);
    public int eliminar(CamaraDTO camara);
    public CamaraDTO obtenerPorId(Integer id);
    public ArrayList<CamaraDTO> listarTodos();
}