/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.com.transitsoft.transitsoftws;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.ArrayList;
import java.util.List;
import pe.com.transitsoft.transitsoftbusiness.CapturasBO;
import pe.com.transitsoft.transitsoftmodel.Captura;

/**
 *
 * @author Usuario
 */
@WebService(serviceName = "Capturas")
public class Capturas {
    private CapturasBO capturasBO;
    
    public Capturas(){
        this.capturasBO = new CapturasBO();
    }
    
    @WebMethod(operationName = "retornarCapturas")
    public ArrayList<Captura> retornarCapturas(){
        List<Object> cap = this.capturasBO.retornarCapturas();
        ArrayList<Captura> capturas = new ArrayList<>();
        for(Object lista : cap){
            List<Object> datos = (List<Object>) lista;
            Captura captura = (Captura) datos.get(0);
            capturas.add(captura);
        }
        return capturas;
    }
    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
}
