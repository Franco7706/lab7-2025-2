package pe.edu.pucp.transitsoft.bo;

import java.util.ArrayList;
import pe.edu.pucp.transitsoft.daoImp.VehiculoDAOImpl;
import pe.edu.pucp.transitsoft.bo.util.SeleccionMotorBD;
import pe.edu.pucp.transitsoft.dao.VehiculoDAO;
import pe.edu.pucp.transitsoft.model.VehiculoDTO;


public class VehiculoBO {

    private VehiculoDAO vehiculoDAO;
    
    public VehiculoBO(){
        this.vehiculoDAO = new VehiculoDAOImpl();
    }
    
    public ArrayList<VehiculoDTO> listarTodos(String motor){
        return vehiculoDAO.listarTodos(SeleccionMotorBD.obtenerMotor(motor));
    }
    
    public VehiculoDTO obtenerPorId(int vehiculoId,String motor){
        return vehiculoDAO.obtenerPorId(vehiculoId, SeleccionMotorBD.obtenerMotor(motor));
    }
    
    public int eliminar(int vehiculoId,String motor){
        return vehiculoDAO.eliminar(vehiculoId, SeleccionMotorBD.obtenerMotor(motor));
    }
    
    public int modificar(VehiculoDTO vehiculo,String motor){
        return vehiculoDAO.modificar(vehiculo, SeleccionMotorBD.obtenerMotor(motor));
    }
    
    public int insertar(VehiculoDTO vehiculo,String motor){
        if(vehiculo.getPropietario()==null)
            return 0;
        int id = vehiculoDAO.insertar(vehiculo, SeleccionMotorBD.obtenerMotor(motor));
        return id;
    }

    public int insertarOModificar(VehiculoDTO vehiculoDTO, String motor) {
        VehiculoDTO vehiculoExistente = this.obtenerPorId(vehiculoDTO.getId(), motor);
        if(vehiculoExistente.getPlaca()!=null){
            if(this.modificar(vehiculoDTO, motor)!=0) return -1;
            return 0;
        }
        else{
            int id = this.insertar(vehiculoDTO,motor);
            vehiculoDTO.setId(id);
            return id;
        }
    }
    
}
