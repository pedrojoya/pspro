package es.iessaladillo.pedrojoya.pspro.rama0202;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Filosofo implements Runnable {

    // Propieadades.
    int num; // Número de filósofo.
    Mesa mesa; // Mesa de los comensales.

    // Auxiliares.
    SimpleDateFormat formateador = new SimpleDateFormat("HH:mm:ss");
    Random aleatorioPensar = new Random();
    Random aleatorioComer = new Random();

    // Constructor.
    public Filosofo(int num, Mesa mesa) {
        this.num = num;
        this.mesa = mesa;
    }

    @Override
    public void run() {
        // El filósofo piensa y se alimenta 5 veces.
        for (int i = 0; i < 5; i++) {
            // Primero el filósofo piensa.
            pensar();
            // Después se alimenta.
            alimentarse();
        }
        // El filósofo se va.
        System.out.println(formateador.format(new Date()) + " --> Filósofo "
                + num + ": Abandona la mesa");
    }

    // El filósofo se pone a pensar.
    private void pensar() {
        int tiempo = aleatorioPensar.nextInt(5) + 1;
        System.out.println(formateador.format(new Date()) + " --> Filósofo "
                + num + ": Piensa durante " + tiempo
                + ((tiempo > 1) ? " segundos" : " segundo"));
        try {
            TimeUnit.SECONDS.sleep(tiempo);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // El filósofo quiere alimentarse.
    private void alimentarse() {
        // Coge los palillos.
        mesa.coger(num);
        // Una vez que tiene los palillos puede comer.
        comer();
        // Después de comer suelta los palillos.
        mesa.soltar(num);
    }

    // El filósofo se pone a comer.
    private void comer() {
        int tiempo = aleatorioComer.nextInt(5) + 1;
        System.out.println(formateador.format(new Date()) + " --> Filósofo "
                + num + ": Come durante " + tiempo
                + ((tiempo > 1) ? " segundos" : " segundo"));
        try {
            TimeUnit.SECONDS.sleep(tiempo);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
