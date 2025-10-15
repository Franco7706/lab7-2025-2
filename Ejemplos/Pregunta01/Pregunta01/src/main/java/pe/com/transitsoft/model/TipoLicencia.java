/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.com.transitsoft.model;

/**
 *
 * @author Usuario
 */
public class TipoLicencia {
    private int tipoLicenciaId;
    private String nombre;
    private String descripcion;

    /**
     * @return the tipoLicenciaId
     */
    public int getTipoLicenciaId() {
        return tipoLicenciaId;
    }

    /**
     * @param tipoLicenciaId the tipoLicenciaId to set
     */
    public void setTipoLicenciaId(int tipoLicenciaId) {
        this.tipoLicenciaId = tipoLicenciaId;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
