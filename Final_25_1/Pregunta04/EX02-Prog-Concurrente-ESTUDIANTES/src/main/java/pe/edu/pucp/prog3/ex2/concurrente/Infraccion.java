package pe.edu.pucp.prog3.ex2.concurrente;


public class Infraccion {
    private final String codigo;
    private final double monto;
    private final int puntos;
    private final Captura captura;

    public Infraccion(String codigo, double monto, int puntos, Captura captura) {
        this.codigo = codigo;
        this.monto = monto;
        this.puntos = puntos;
        this.captura = captura;
    }

    @Override
    public String toString() {
        return "==== INFRACCIÓN ====\n" +
                String.format("Código : %s%n", codigo) +
                String.format("Monto  : %.2f%n", monto) +
                String.format("Puntos : %d%n", puntos) +
                "-----------------\n" +
                "* CAPTURA *\n" +
                captura + "\n" +
                "=================\n";
    }
}