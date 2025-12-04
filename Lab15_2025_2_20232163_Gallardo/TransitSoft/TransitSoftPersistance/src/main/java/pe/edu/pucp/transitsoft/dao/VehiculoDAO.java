package pe.edu.pucp.transitsoft.dao;

import java.util.ArrayList;
import pe.edu.pucp.transitsoft.model.VehiculoDTO;
import pe.edu.pucp.transitsoft.util.MotorDeBaseDeDatos;

public interface VehiculoDAO {
    
    public ArrayList<VehiculoDTO> listarTodos(MotorDeBaseDeDatos motor);
    
    public VehiculoDTO obtenerPorId(int vehiculoId,MotorDeBaseDeDatos motor);
    
    public int eliminar(int vehiculoId,MotorDeBaseDeDatos motor);
    
    public int modificar(VehiculoDTO vehiculo,MotorDeBaseDeDatos motor);
    
    public int insertar(VehiculoDTO vehiculo,MotorDeBaseDeDatos motor);
}
