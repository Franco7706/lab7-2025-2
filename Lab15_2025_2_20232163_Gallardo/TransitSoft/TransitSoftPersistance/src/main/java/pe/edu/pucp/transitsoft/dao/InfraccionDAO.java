package pe.edu.pucp.transitsoft.dao;

import java.util.ArrayList;
import pe.edu.pucp.transitsoft.model.InfraccionDTO;
import pe.edu.pucp.transitsoft.util.MotorDeBaseDeDatos;

public interface InfraccionDAO {
    public ArrayList<InfraccionDTO> listarTodos(MotorDeBaseDeDatos motor);
    
    public InfraccionDTO obtenerPorId(int infraccionId,MotorDeBaseDeDatos motor);
    
    public int eliminar(int infraccionId,MotorDeBaseDeDatos motor);
    
    public int modificar(InfraccionDTO infraccion,MotorDeBaseDeDatos motor);
    
    public int insertar(InfraccionDTO infraccion,MotorDeBaseDeDatos motor);
}
