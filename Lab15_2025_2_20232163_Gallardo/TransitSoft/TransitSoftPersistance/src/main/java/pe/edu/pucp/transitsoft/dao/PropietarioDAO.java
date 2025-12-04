/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.transitsoft.dao;

import java.util.ArrayList;
import pe.edu.pucp.transitsoft.model.PropietarioDTO;
import pe.edu.pucp.transitsoft.util.MotorDeBaseDeDatos;

/**
 *
 * @author alulab14
 */
public interface PropietarioDAO {
    public ArrayList<PropietarioDTO> listarTodos(MotorDeBaseDeDatos motor);
    
    public PropietarioDTO obtenerPorId(int propietarioId,MotorDeBaseDeDatos motor);
    
    public int eliminar(int propietarioId,MotorDeBaseDeDatos motor);
    
    public int modificar(PropietarioDTO propietario,MotorDeBaseDeDatos motor);
    
    public int insertar(PropietarioDTO propietario,MotorDeBaseDeDatos motor);
}
