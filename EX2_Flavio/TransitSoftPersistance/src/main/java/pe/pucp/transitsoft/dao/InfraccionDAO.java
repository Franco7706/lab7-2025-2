/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.pucp.transitsoft.dao;

import java.util.ArrayList;
import pe.pucp.transitsoft.model.InfraccionDTO;

/**
 *
 * @author Flavio
 */
public interface InfraccionDAO {
    public int insertar(InfraccionDTO captura);
    public ArrayList<InfraccionDTO> listarTodos();
}
