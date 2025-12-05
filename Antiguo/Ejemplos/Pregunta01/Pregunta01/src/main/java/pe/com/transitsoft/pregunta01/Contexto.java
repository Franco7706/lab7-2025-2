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
public class Contexto {

    private ArrayList<Conductor> conductores;
    private ArrayList<VehiculoConductor> vehiculosConductor;
    private ArrayList<RegistroInfraccion> registrosInfracciones;
    
    private Estrategia estrategia;

    public Contexto(Estrategia estrategia) {
        this.estrategia = estrategia;
    }

    public void ejecutarEstrategia() {
        conductores = new ArrayList<>();
        vehiculosConductor = new ArrayList<>();
        registrosInfracciones = new ArrayList<>();
        Datos.cargarDatos(conductores, vehiculosConductor, registrosInfracciones);
        estrategia.ejecutar(conductores,vehiculosConductor,registrosInfracciones);
    }

    public void setEstrategia(Estrategia estrategia) {
        this.estrategia = estrategia;
    }
    
    public void imprimirResultados(){
        System.out.println("Puntos acumulados de los conductores:");
        for(Conductor conductor : conductores){
            System.out.println("Conductor " + conductor.getConductorId()
            + ": " + conductor.getPuntosAcumulados());
        }
        System.out.println();
    }
}
