package pe.edu.pucp.prog3.ex2.concurrente;

import java.util.Random;


public class Trafico extends Thread {
    private FlujoVehicular flujoVehicular;

    private final Random random = new Random();
    private final String[] PREFIJOS_PLACA = { "YHG", "BFL", "RTG", "LMN", "PQR", "UVW" };
    private int contadorVehiculos = 0;

    public Trafico(final FlujoVehicular flujoVehicular) {
        this.flujoVehicular = flujoVehicular;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            int nroVehiculos = random.nextInt(5, 10);
            for (int i = 0; i < nroVehiculos; i++) {
                String placa = generarPlaca();
                int velocidad = random.nextInt(40, 120);
                this.flujoVehicular.agregarVehiculo(new Vehiculo(placa, velocidad));
            }

            try {
                Thread.sleep(500);
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    
    private String generarPlaca() {
        int nroPrefijos = PREFIJOS_PLACA.length;
        int indicePrefijo = random.nextInt(nroPrefijos);
        String prefijo = PREFIJOS_PLACA[indicePrefijo];
        return prefijo + "-" + String.format("%03d", ++contadorVehiculos);
    }
}
