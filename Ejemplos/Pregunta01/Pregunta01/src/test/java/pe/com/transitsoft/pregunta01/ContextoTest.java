/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pe.com.transitsoft.pregunta01;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Usuario
 */
public class ContextoTest {
    
    private Contexto contexto;
    
    public ContextoTest() {
    }

    /**
     * Test of ejecutarEstrategia method, of class Contexto.
     */
    @org.junit.jupiter.api.Test
    public void testEjecutarEstrategias() {
        contexto = new Contexto(new Estrategia1());
        contexto.ejecutarEstrategia();
        contexto.imprimirResultados();
        contexto.setEstrategia(new Estrategia2());
        contexto.ejecutarEstrategia();
        contexto.imprimirResultados();
        contexto.setEstrategia(new Estrategia3());
        contexto.ejecutarEstrategia();
        contexto.imprimirResultados();
    }

    
}
