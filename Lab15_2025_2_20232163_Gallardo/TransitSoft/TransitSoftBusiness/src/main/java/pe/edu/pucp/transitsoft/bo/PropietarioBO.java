package pe.edu.pucp.transitsoft.bo;

import java.util.ArrayList;
import pe.edu.pucp.transitsoft.daoImp.PropietarioDAOImpl;
import pe.edu.pucp.transitsoft.bo.util.SeleccionMotorBD;
import pe.edu.pucp.transitsoft.dao.PropietarioDAO;
import pe.edu.pucp.transitsoft.model.PropietarioDTO;
import pe.edu.pucp.transitsoft.model.VehiculoDTO;


public class PropietarioBO {

    private PropietarioDAO propietarioDAO;
    
    public PropietarioBO(){
        this.propietarioDAO = new PropietarioDAOImpl();
    }
    
    public ArrayList<PropietarioDTO> listarTodos(String motor){
        return propietarioDAO.listarTodos(SeleccionMotorBD.obtenerMotor(motor));
    }
    
    public PropietarioDTO obtenerPorId(int propietarioId,String motor){
        return propietarioDAO.obtenerPorId(propietarioId, SeleccionMotorBD.obtenerMotor(motor));
    }
    
    public int eliminar(int propietarioId,String motor){
        return propietarioDAO.eliminar(propietarioId, SeleccionMotorBD.obtenerMotor(motor));
    }
    
    public int modificar(PropietarioDTO propietario,String motor){
        return propietarioDAO.modificar(propietario, SeleccionMotorBD.obtenerMotor(motor));
    }
    
    public int insertar(PropietarioDTO propietario,String motor){
        return propietarioDAO.insertar(propietario, SeleccionMotorBD.obtenerMotor(motor));
    }

    public PropietarioDTO obtenerPropietarioVehiculo(Integer vehiculoId, String motor) {
        VehiculoBO vehiculoBO = new VehiculoBO();
        VehiculoDTO vehiculo = vehiculoBO.obtenerPorId(vehiculoId, motor);
        if(vehiculo==null || vehiculo.getPropietario()==null){
            return new PropietarioDTO();
        }
        return vehiculo.getPropietario();
    }
    
}
