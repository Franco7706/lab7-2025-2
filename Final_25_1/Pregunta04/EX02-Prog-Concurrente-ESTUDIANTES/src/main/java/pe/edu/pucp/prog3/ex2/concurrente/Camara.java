package pe.edu.pucp.prog3.ex2.concurrente;


public class Camara extends Thread {
    private final FlujoVehicular flujoVehicular;
    private final RegistroInfracciones registroInfracciones;

    private final int VELOCIDAD_MAXIMA = 80;
    private final String CODIGO = "M20";
    private final double MONTO = 950.00;
    private final int PUNTOS = 50;

    public Camara(final FlujoVehicular flujoVehicular, final RegistroInfracciones registroInfracciones) {
        this.flujoVehicular = flujoVehicular;
        this.registroInfracciones = registroInfracciones;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            // TODO: procesar flujo vial - capturas y detección de infracciones
        }
    }
}
