package pe.edu.pucp.transitsoft.dao;

import java.util.ArrayList;
import pe.edu.pucp.transitsoft.model.PropietarioDTO;
import pe.edu.pucp.transitsoft.util.MotorDeBaseDeDatos;


public interface PropietarioDAO {
    public ArrayList<PropietarioDTO> listarTodos(MotorDeBaseDeDatos motor);
    
    public PropietarioDTO obtenerPorId(int propietarioId,MotorDeBaseDeDatos motor);
    
    public int eliminar(int propietarioId,MotorDeBaseDeDatos motor);
    
    public int modificar(PropietarioDTO propietario,MotorDeBaseDeDatos motor);
    
    public int insertar(PropietarioDTO propietario,MotorDeBaseDeDatos motor);
}
