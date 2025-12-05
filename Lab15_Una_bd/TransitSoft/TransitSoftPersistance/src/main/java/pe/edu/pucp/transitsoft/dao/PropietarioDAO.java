package pe.edu.pucp.transitsoft.dao;

import java.util.ArrayList;
import pe.edu.pucp.transitsoft.model.PropietarioDTO;


public interface PropietarioDAO {
    public ArrayList<PropietarioDTO> listarTodos();
    
    public PropietarioDTO obtenerPorId(int propietarioId);
    
    public int eliminar(int propietarioId);
    
    public int modificar(PropietarioDTO propietario);
    
    public int insertar(PropietarioDTO propietario);
}
