/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.com.transitsoft.pregunta01;

import java.util.ArrayList;
import pe.com.transitsoft.model.Conductor;
import pe.com.transitsoft.model.RegistroInfraccion;
import pe.com.transitsoft.model.VehiculoConductor;

/**
 *
 * @author Usuario
 */
public interface Estrategia {
    void ejecutar(
            ArrayList<Conductor> conductores,
            ArrayList<VehiculoConductor> vehiculosConductor,
            ArrayList<RegistroInfraccion> registrosInfracciones);
}
