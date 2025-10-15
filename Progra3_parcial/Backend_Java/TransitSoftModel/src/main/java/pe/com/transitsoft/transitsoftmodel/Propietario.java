package pe.com.transitsoft.transitsoftmodel;

public class Propietario {
    private int propietarioId;
    private String dni;
    private String nombres;
    private String apellidos;
    private String direccion;
    
    public Propietario(){
        propietarioId = 0;
        dni = null;
        nombres = null;
        apellidos = null;
        direccion = null;
    }

    public Propietario(int propietarioId, String dni, String nombres, String apellidos, String direccion){
        this.propietarioId=propietarioId;
        this.dni=dni;
        this.nombres=nombres;
        this.apellidos=apellidos;
        this.direccion=direccion;
    }
    
    public int getPropietarioId() {
        return propietarioId;
    }

    public void setPropietarioId(int propietarioId) {
        this.propietarioId = propietarioId;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
}
