package es.iessaladillo.pedrojoya.pspro.rama0203;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        // Se crea el objeto Barberia.
        Barberia barberia = new Barberia(5);
        // Se crea e inicia el hilo del Barbero.
        (new Thread(new Barbero(barberia))).start();
        // Simulamos el tiempo que tarda en llegar el primer cliente.
        Random aleatorio = new Random();
        try {
            TimeUnit.SECONDS.sleep(aleatorio.nextInt(5));
        } catch (InterruptedException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        // Se crean 20 hilos clientes con segundos de diferencia aleatorios
        // entre ellos.
        for (int i = 0; i < 20; i++) {
            (new Thread(new Cliente(i, barberia))).start();
            try {
                TimeUnit.SECONDS.sleep(aleatorio.nextInt(4));
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
