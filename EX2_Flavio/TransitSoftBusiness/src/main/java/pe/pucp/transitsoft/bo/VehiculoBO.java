package pe.pucp.transitsoft.bo;

import java.util.ArrayList;
import pe.edu.pucp.softinv.daoImp.VehiculoDAOImpl;
import pe.pucp.transitsoft.dao.VehiculoDAO;
import pe.pucp.transitsoft.model.VehiculoDTO;


public class VehiculoBO {

    private VehiculoDAO vehiculoDAO;
    
    public VehiculoBO(){
        this.vehiculoDAO = new VehiculoDAOImpl();
    }
    
    public int insertar(VehiculoDTO vehiculo) {
        return this.vehiculoDAO.insertar(vehiculo);
    }
    
    public int modificar(VehiculoDTO vehiculo) {
        return this.vehiculoDAO.modificar(vehiculo);
    }
    
    public int eliminar(Integer id) {
        VehiculoDTO vehiculo = new VehiculoDTO();
        vehiculo.setId(id);
        return this.vehiculoDAO.eliminar(vehiculo);
    }
    
    public VehiculoDTO obtenerPorId(Integer id) {
        return this.vehiculoDAO.obtenerPorId(id);
    }
    
    public ArrayList<VehiculoDTO> listarTodos() {
        return this.vehiculoDAO.listarTodos();
    }
    
    public ArrayList<VehiculoDTO> listarPorPropietario(String descripion_propietario){
        return this.vehiculoDAO.listarPorPropietario(descripion_propietario);
    }
    
}
