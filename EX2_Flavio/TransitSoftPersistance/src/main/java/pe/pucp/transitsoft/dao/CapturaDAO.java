/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.pucp.transitsoft.dao;

import java.util.ArrayList;
import pe.pucp.transitsoft.model.CapturaDTO;

/**
 *
 * @author Flavio
 */
public interface CapturaDAO {
    public int insertar(CapturaDTO captura);
    public int modificar(CapturaDTO captura);
    public int eliminar(CapturaDTO captura);
    public CapturaDTO obtenerPorId(Integer id);
    public ArrayList<CapturaDTO> listarTodos();
}
