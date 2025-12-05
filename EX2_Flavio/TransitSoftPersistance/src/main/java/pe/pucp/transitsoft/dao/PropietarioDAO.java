/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.pucp.transitsoft.dao;

import java.util.ArrayList;
import pe.pucp.transitsoft.model.PropietarioDTO;
/**
 *
 * @author alulab14
 */
public interface PropietarioDAO {
    public int insertar(PropietarioDTO propietario);
    public int modificar(PropietarioDTO propietario);
    public int eliminar(PropietarioDTO propietario);
    public PropietarioDTO obtenerPorId(Integer id);
    public ArrayList<PropietarioDTO> listarTodos();
}
