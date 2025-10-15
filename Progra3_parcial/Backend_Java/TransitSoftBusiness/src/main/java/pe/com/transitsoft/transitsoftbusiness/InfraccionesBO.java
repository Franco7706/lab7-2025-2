/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.transitsoft.transitsoftbusiness;

import java.util.Date;
import java.util.List;
import pe.com.transitsoft.transitsoftmodel.Camara;
import pe.com.transitsoft.transitsoftmodel.Captura;
import pe.com.transitsoft.transitsoftmodel.Infraccion;
import pe.com.transitsoft.transitsoftmodel.Propietario;
import pe.com.transitsoft.transitsoftmodel.Vehiculo;
import pe.com.transitsoft.transitsoftpersistance.dao.InfraccionDAO;
import pe.com.transitsoft.transitsoftpersistance.daoimpl.InfraccionDAOImpl;

/**
 *
 * @author Usuario
 */
public class InfraccionesBO {
    private InfraccionDAO infraccionDAO;
    
    public InfraccionesBO(){
        infraccionDAO = new InfraccionDAOImpl();
    }
    
    public void registrarInfracciones(List<Object> capturas){
        Double limite,monto;
        Infraccion infraccion;
        for(Object lista : capturas){
            List<Object> datos = (List<Object>) lista;
            Captura captura = (Captura) datos.get(0);
            Camara camara = (Camara) datos.get(1);
            Propietario propietario = (Propietario) datos.get(2);
            Vehiculo vehiculo = (Vehiculo) datos.get(3);
            if("CAM-LIM-001".equals(camara.getCodigoSerie())){ // logica arbitraria, como sea
                limite = 80.0;
            }
            else{
                limite = 60.0;
            }
            monto = 5050.0;
            if(captura.getVelocidad()>limite){
                infraccion = new Infraccion(0,vehiculo.getPlaca(),captura.getVelocidad(),limite,captura.getVelocidad()-limite,vehiculo.getMarca(),vehiculo.getModelo(),
                vehiculo.getAnho(),propietario.getDni(),propietario.getNombres(),propietario.getApellidos(),propietario.getDireccion(),camara.getCamaraId(),camara.getModelo(),
                camara.getCodigoSerie(),camara.getLatitud(),camara.getLongitud(),monto,captura.getFechaCaptura(),new Date());
                System.out.println(infraccionDAO.insertarInfraccion(infraccion));
            }
        }
    }
}
