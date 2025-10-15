/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.transitsoft.transitsoftbusiness;
import java.util.List;
import pe.com.transitsoft.transitsoftmodel.Camara;
import pe.com.transitsoft.transitsoftmodel.Captura;
import pe.com.transitsoft.transitsoftmodel.Propietario;
import pe.com.transitsoft.transitsoftmodel.Vehiculo;
import pe.com.transitsoft.transitsoftpersistance.dao.CapturaDAO;
import pe.com.transitsoft.transitsoftpersistance.daoimpl.CapturaDAOImpl;
/**
 *
 * @author Usuario
 */
public class CapturasBO {
    private CapturaDAO capturaDAO;
    
    public CapturasBO(){
        capturaDAO = new CapturaDAOImpl();
    }
    
    public void listarCapturas(){
        List<Object> capturas = capturaDAO.listarCapturas();
        for(Object objeto : capturas){
            List<Object> datos = (List<Object>) objeto;
            Captura captura = (Captura) datos.get(0);
            Camara camara = (Camara) datos.get(1);
            Propietario propietario = (Propietario) datos.get(2);
            Vehiculo vehiculo = (Vehiculo) datos.get(3);
            System.out.println(captura.getFechaCaptura() + ", " + camara.getCodigoSerie() + ", " + propietario.getDni() + ", " + vehiculo.getPlaca());
            capturaDAO.ModificarEstadoCaptura(captura);
        }
    }
    
    public List<Object> retornarCapturas(){
        List<Object> capturas = capturaDAO.listarCapturas();
        return capturas;
    }
}
