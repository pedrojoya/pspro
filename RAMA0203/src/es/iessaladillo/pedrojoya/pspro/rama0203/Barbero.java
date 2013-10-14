package es.iessaladillo.pedrojoya.pspro.rama0203;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Barbero implements Runnable {

    // Propieadades.
    Barberia barberia; // Barbería en la que trabaja.

    // Auxiliares.
    SimpleDateFormat formateador = new SimpleDateFormat("HH:mm:ss");
    Random aleatorio = new Random();

    // Constructor
    public Barbero(Barberia barberia) {
        this.barberia = barberia;
    }

    @Override
    public void run() {
        // Trabaja continuamente.
        while (true) {
            // Obtiene el siguiente cliente a atender.
            int numCliente = barberia.siguiente();
            // Atiende al cliente obtenido.
            atender(numCliente);
        }
    }

    // El barbero atiende a un cliente.
    private void atender(int numCliente) {
        // Se obtiene el tiempo que tarda el barbero en pelar al cliente.
        int tiempo = aleatorio.nextInt(5) + 1;
        // Se simula el pelado.
        try {
            TimeUnit.SECONDS.sleep(tiempo);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // Se informa.
        System.out.println(formateador.format(new Date()) + " --> Cliente "
                + numCliente + " atendido durante " + tiempo
                + ((tiempo > 1) ? " segundos" : " segundo"));

    }

}
