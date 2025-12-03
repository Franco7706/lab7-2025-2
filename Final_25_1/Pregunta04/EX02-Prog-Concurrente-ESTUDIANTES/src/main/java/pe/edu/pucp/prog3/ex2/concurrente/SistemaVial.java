package pe.edu.pucp.prog3.ex2.concurrente;


public class SistemaVial {
    public static void main(String[] args) {
        FlujoVehicular flujoVehicular = new FlujoVehicular();
        RegistroInfracciones registroInfracciones = new RegistroInfracciones();

        Camara cam1 = new Camara(flujoVehicular, registroInfracciones);
        Camara cam2 = new Camara(flujoVehicular, registroInfracciones);
        Camara cam3 = new Camara(flujoVehicular, registroInfracciones);
        Camara cam4 = new Camara(flujoVehicular, registroInfracciones);
        Trafico trafico = new Trafico(flujoVehicular);

        // Encender las cámaras
        cam1.start();
        cam2.start();
        cam3.start();
        cam4.start();
        // Iniciar el transito vehicular
        trafico.start();

        try {
            // Mantener el sistema víal funcionando por 10 segundos
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Detener el transito vehicular
        trafico.interrupt();
        // Apagar las cámaras
        cam1.interrupt();
        cam2.interrupt();
        cam3.interrupt();
        cam4.interrupt();

        try {
            cam1.join();
            cam2.join();
            cam3.join();
            cam4.join();
            trafico.join();
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println();
        System.out.println(registroInfracciones);
    }
}
