package pe.edu.pucp.transitsoft.dao;

import java.util.ArrayList;
import pe.edu.pucp.transitsoft.model.InfraccionDTO;

public interface InfraccionDAO {
    public ArrayList<InfraccionDTO> listarTodos();
    
    public InfraccionDTO obtenerPorId(int infraccionId);
    
    public int eliminar(int infraccionId);
    
    public int modificar(InfraccionDTO infraccion);
    
    public int insertar(InfraccionDTO infraccion);
}
