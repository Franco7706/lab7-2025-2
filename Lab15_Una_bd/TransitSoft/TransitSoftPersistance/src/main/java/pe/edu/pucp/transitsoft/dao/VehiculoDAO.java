package pe.edu.pucp.transitsoft.dao;

import java.util.ArrayList;
import pe.edu.pucp.transitsoft.model.VehiculoDTO;

public interface VehiculoDAO {
    
    public ArrayList<VehiculoDTO> listarTodos();
    
    public VehiculoDTO obtenerPorId(int vehiculoId);
    
    public int eliminar(int vehiculoId);
    
    public int modificar(VehiculoDTO vehiculo);
    
    public int insertar(VehiculoDTO vehiculo);
}
