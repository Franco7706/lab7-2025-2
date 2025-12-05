package pe.edu.pucp.transitsoft.dao;

import java.util.ArrayList;
import pe.edu.pucp.transitsoft.model.CamaraDTO;
import pe.edu.pucp.transitsoft.util.MotorDeBaseDeDatos;

public interface CamaraDAO {
    public ArrayList<CamaraDTO> listarTodos(MotorDeBaseDeDatos motor);
    
    public CamaraDTO obtenerPorId(int camaraId,MotorDeBaseDeDatos motor);
    
    public int eliminar(int camaraId,MotorDeBaseDeDatos motor);
    
    public int modificar(CamaraDTO camara,MotorDeBaseDeDatos motor);
    
    public int insertar(CamaraDTO camara,MotorDeBaseDeDatos motor);
}
