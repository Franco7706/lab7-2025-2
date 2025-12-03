package pe.edu.pucp.transitsoftbusiness;

import java.util.ArrayList;
import pe.edu.pucp.transitsoft.dao.ConductorDAO;
import pe.edu.pucp.transitsoft.dao.VehiculoDAO;
import pe.edu.pucp.transitsoft.daoImp.ConductorDAOImpl;
import pe.edu.pucp.transitsoft.daoImp.VehiculoDAOImpl;
import pe.edu.pucp.transitsoft.model.ConductoresDTO;
import pe.edu.pucp.transitsoft.model.VehiculosDTO;

public class ConductorBO {
    
    private ConductorDAO conductorDAO;
    private VehiculoDAO vehiculoDAO;
    
    public ConductorBO() {
        conductorDAO = new ConductorDAOImpl();
        vehiculoDAO = new VehiculoDAOImpl();
    }
    
    public ArrayList<ConductoresDTO> listarConductores(int tipoLicencia){
        ArrayList<ConductoresDTO> conductores = conductorDAO.listarTodos();
        if(tipoLicencia==0) return conductores;
        ArrayList<ConductoresDTO> seleccionados = new ArrayList<>();
        for(ConductoresDTO conductor : conductores){
            if(conductor.getIdTipoLicencia()==tipoLicencia){
                seleccionados.add(conductor);
            }
        }
        return seleccionados;
    }
    
    public ArrayList<VehiculosDTO> listarVehiculosConductor(String numeroLicencia){
        ArrayList<ConductoresDTO> conductores = conductorDAO.listarTodos();
        ConductoresDTO conductor = new ConductoresDTO();
        for(ConductoresDTO conductor_actual : conductores){
            if(conductor_actual.getNumeroLicencia().equals(numeroLicencia)){
                conductor = conductor_actual;
                break;
            }
        }
        if(conductor.getApellidoMaterno()==null) return new ArrayList<>();
        ArrayList<VehiculosDTO> vehiculo = vehiculoDAO.listarTodos();
        ArrayList<VehiculosDTO> seleccionados = new ArrayList<>();
        for(VehiculosDTO vehiculo_actual : vehiculo){
            if(vehiculo_actual.getIdConductor()==conductor.getIdConductor()){
                seleccionados.add(vehiculo_actual);
            }
        }
        return seleccionados;
    }
    
    public int insertarConductor(ConductoresDTO conductor){
        int id = conductorDAO.insertar(conductor);
        if(id==0) return 0;
        conductor.setIdConductor(id);
        conductor.getVehiculo().setIdConductor(conductor.getIdConductor());
        vehiculoDAO.insertar(conductor.getVehiculo());
        return conductor.getIdConductor();
    }
}
