package pe.edu.pucp.transitsoft.dao;

import java.util.ArrayList;
import pe.edu.pucp.transitsoft.model.CamaraDTO;

public interface CamaraDAO {
    public ArrayList<CamaraDTO> listarTodos();
    
    public CamaraDTO obtenerPorId(int camaraId);
    
    public int eliminar(int camaraId);
    
    public int modificar(CamaraDTO camara);
    
    public int insertar(CamaraDTO camara);
}
