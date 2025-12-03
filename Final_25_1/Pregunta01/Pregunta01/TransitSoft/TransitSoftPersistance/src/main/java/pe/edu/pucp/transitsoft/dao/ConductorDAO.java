package pe.edu.pucp.transitsoft.dao;

import java.util.ArrayList;
import pe.edu.pucp.transitsoft.model.ConductoresDTO;

public interface ConductorDAO {
    
    public Integer insertar(ConductoresDTO conductor);
    
    public ConductoresDTO obtenerPorId(Integer conductorId);
    
    public ArrayList<ConductoresDTO> listarTodos();
    
    public Integer modificar(ConductoresDTO conductor);
    
    public Integer eliminar(ConductoresDTO conductor);
}
