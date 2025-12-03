package pe.edu.pucp.transitsoft.dao;

import java.util.ArrayList;
import pe.edu.pucp.transitsoft.model.VehiculosDTO;

public interface VehiculoDAO {
    
    public Integer insertar(VehiculosDTO vehiculo);
    
    public VehiculosDTO obtenerPorId(Integer vehiculoId);
    
    public ArrayList<VehiculosDTO> listarTodos();
    
    public Integer modificar(VehiculosDTO vehiculo);
    
    public Integer eliminar(VehiculosDTO vehiculo);
}
