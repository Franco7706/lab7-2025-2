package pe.edu.pucp.transitsoft.dao;

import java.util.ArrayList;
import pe.edu.pucp.transitsoft.model.CapturaDTO;
import pe.edu.pucp.transitsoft.util.MotorDeBaseDeDatos;

public interface CapturaDAO {
    public ArrayList<CapturaDTO> listarTodos(MotorDeBaseDeDatos motor);
    
    public CapturaDTO obtenerPorId(int capturaId,MotorDeBaseDeDatos motor);
    
    public int eliminar(int capturaId,MotorDeBaseDeDatos motor);
    
    public int modificar(CapturaDTO captura,MotorDeBaseDeDatos motor);
    
    public int insertar(CapturaDTO captura,MotorDeBaseDeDatos motor);
}
