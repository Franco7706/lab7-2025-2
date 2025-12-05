package pe.edu.pucp.transitsoft.bo;

import java.util.ArrayList;
import pe.edu.pucp.transitsoft.daoImp.PropietarioDAOImpl;
import pe.edu.pucp.transitsoft.dao.PropietarioDAO;
import pe.edu.pucp.transitsoft.model.PropietarioDTO;
import pe.edu.pucp.transitsoft.model.VehiculoDTO;


public class PropietarioBO {

    private PropietarioDAO propietarioDAO;
    
    public PropietarioBO(){
        this.propietarioDAO = new PropietarioDAOImpl();
    }
    
    public ArrayList<PropietarioDTO> listarTodos(){
        return propietarioDAO.listarTodos();
    }
    
    public PropietarioDTO obtenerPorId(int propietarioId){
        return propietarioDAO.obtenerPorId(propietarioId);
    }
    
    public int eliminar(int propietarioId){
        return propietarioDAO.eliminar(propietarioId);
    }
    
    public int modificar(PropietarioDTO propietario){
        return propietarioDAO.modificar(propietario);
    }
    
    public int insertar(PropietarioDTO propietario){
        return propietarioDAO.insertar(propietario);
    }

    public PropietarioDTO obtenerPropietarioVehiculo(Integer vehiculoId) {
        VehiculoBO vehiculoBO = new VehiculoBO();
        VehiculoDTO vehiculo = vehiculoBO.obtenerPorId(vehiculoId);
        if(vehiculo==null || vehiculo.getPropietario()==null){
            return new PropietarioDTO();
        }
        return vehiculo.getPropietario();
    }
    
}
