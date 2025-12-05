package pe.edu.pucp.transitsoft.dao;

import java.util.ArrayList;
import pe.edu.pucp.transitsoft.model.CapturaDTO;

public interface CapturaDAO {
    public ArrayList<CapturaDTO> listarTodos();
    
    public CapturaDTO obtenerPorId(int capturaId);
    
    public int eliminar(int capturaId);
    
    public int modificar(CapturaDTO captura);
    
    public int insertar(CapturaDTO captura);
}
