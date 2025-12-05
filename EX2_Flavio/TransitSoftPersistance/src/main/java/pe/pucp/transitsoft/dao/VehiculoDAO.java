package pe.pucp.transitsoft.dao;

import java.util.ArrayList;
import pe.pucp.transitsoft.model.VehiculoDTO;

public interface VehiculoDAO {
    
    public ArrayList<VehiculoDTO> listarPorPropietario(String descripion_propietario);
    public int insertar(VehiculoDTO vehiculo);
    public int modificar(VehiculoDTO vehiculo);
    public int eliminar(VehiculoDTO vehiculo);
    public VehiculoDTO obtenerPorId(Integer id);
    public ArrayList<VehiculoDTO> listarTodos();
}
