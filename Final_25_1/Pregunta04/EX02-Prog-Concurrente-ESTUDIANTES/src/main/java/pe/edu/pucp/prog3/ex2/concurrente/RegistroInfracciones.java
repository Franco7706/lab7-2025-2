package pe.edu.pucp.prog3.ex2.concurrente;


public class RegistroInfracciones {
    // TODO: Implementar Registro de infracciones
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("*** REGISTRO DE INFRACCIONES ***").append("\n");
        for (Infraccion infraccion : infracciones) {
            sb.append(infraccion).append("\n");
        }
        return sb.toString();
    }
}
