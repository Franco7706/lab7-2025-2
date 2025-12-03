package pe.edu.pucp.transitsoftbusiness;

import java.util.ArrayList;
import pe.edu.pucp.transitsoft.dao.ConductorDAO;
import pe.edu.pucp.transitsoft.dao.VehiculoDAO;
import pe.edu.pucp.transitsoft.daoImp.ConductorDAOImpl;
import pe.edu.pucp.transitsoft.daoImp.VehiculoDAOImpl;
import pe.edu.pucp.transitsoft.model.ConductoresDTO;
import pe.edu.pucp.transitsoft.model.VehiculosDTO;

public class VehiculoBO {
    private ConductorDAO conductorDAO;
    private VehiculoDAO vehiculoDAO;
    
    public VehiculoBO() {
        conductorDAO = new ConductorDAOImpl();
        vehiculoDAO = new VehiculoDAOImpl();
    }
    
    public ArrayList<ConductoresDTO> listarConductoresVehiculo(String placa){
        ArrayList<VehiculosDTO> vehiculos = vehiculoDAO.listarTodos();
        VehiculosDTO vehiculo = new VehiculosDTO();
        for(VehiculosDTO vehiculo_actual : vehiculos){
            if(vehiculo_actual.getPlaca().equals(placa)){
                vehiculo=vehiculo_actual;
                break;
            }
        }
        ArrayList<ConductoresDTO> conductores = conductorDAO.listarTodos();
        ArrayList<ConductoresDTO> seleccionados = new ArrayList<>();
        for(ConductoresDTO conductor : conductores){
            if(conductor.getVehiculo().getIdVehiculo()==vehiculo.getIdVehiculo()){
                seleccionados.add(conductor);
            }
        }
        return seleccionados;
    }
}
