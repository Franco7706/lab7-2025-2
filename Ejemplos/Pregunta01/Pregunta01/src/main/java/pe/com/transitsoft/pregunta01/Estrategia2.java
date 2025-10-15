/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
public class Estrategia2 implements Estrategia {

    @Override
    public void ejecutar(ArrayList<Conductor> conductores,
            ArrayList<VehiculoConductor> vehiculosConductor,
            ArrayList<RegistroInfraccion> registrosInfracciones) {
        for (Conductor conductor : conductores) {
            for (RegistroInfraccion registroInfraccion : registrosInfracciones) {
                for (VehiculoConductor vehiculoConductor : vehiculosConductor) {
                    if (registroInfraccion.getVehiculo().getVehiculoId()
                            == vehiculoConductor.getVehiculo().getVehiculoId()
                            && vehiculoConductor.getConductor().getConductorId()
                            == conductor.getConductorId()
                            && vehiculoConductor.getFechaAdquisicion().before(
                                    registroInfraccion.getFecha())) {
                        conductor.setPuntosAcumulados(conductor.getPuntosAcumulados() + registroInfraccion.getInfraccion().getPuntos());
                    }
                }
            }
        }
    }

}
