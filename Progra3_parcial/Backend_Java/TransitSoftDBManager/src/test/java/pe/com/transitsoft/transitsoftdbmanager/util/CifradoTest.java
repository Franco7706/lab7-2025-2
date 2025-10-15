/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pe.com.transitsoft.transitsoftdbmanager.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Usuario
 */
public class CifradoTest {
    
    public CifradoTest() {
    }

    /**
     * Test of cifrarMD5 method, of class Cifrado.
     */
    @Test
    public void testCifrarMD5() {
        System.out.println(Cifrado.cifrarMD5("franco"));
        System.out.println(Cifrado.cifrarMD5("francogallardo"));
    }

    /**
     * Test of descifrarMD5 method, of class Cifrado.
     */
    @Test
    public void testDescifrarMD5() {
    }
    
}
