package pe.edu.pucp.transitsoft.bo;

import java.util.ArrayList;
import pe.edu.pucp.transitsoft.daoImp.VehiculoDAOImpl;
import pe.edu.pucp.transitsoft.dao.VehiculoDAO;
import pe.edu.pucp.transitsoft.model.VehiculoDTO;


public class VehiculoBO {

    private VehiculoDAO vehiculoDAO;
    
    public VehiculoBO(){
        this.vehiculoDAO = new VehiculoDAOImpl();
    }
    
    public ArrayList<VehiculoDTO> listarTodos(){
        return vehiculoDAO.listarTodos();
    }
    
    public VehiculoDTO obtenerPorId(int vehiculoId){
        return vehiculoDAO.obtenerPorId(vehiculoId);
    }
    
    public int eliminar(int vehiculoId){
        return vehiculoDAO.eliminar(vehiculoId);
    }
    
    public int modificar(VehiculoDTO vehiculo){
        return vehiculoDAO.modificar(vehiculo);
    }
    
    public int insertar(VehiculoDTO vehiculo){
        if(vehiculo.getPropietario()==null)
            return 0;
        int id = vehiculoDAO.insertar(vehiculo);
        return id;
    }

    public int insertarOModificar(VehiculoDTO vehiculoDTO) {
        VehiculoDTO vehiculoExistente = this.obtenerPorId(vehiculoDTO.getId());
        if(vehiculoExistente.getPlaca()!=null){
            if(this.modificar(vehiculoDTO)!=0) return -1;
            return 0;
        }
        else{
            int id = this.insertar(vehiculoDTO);
            vehiculoDTO.setId(id);
            return id;
        }
    }
    
}
