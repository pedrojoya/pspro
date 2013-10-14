package es.iessaladillo.pedrojoya.pspro.rama0202;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Filosofo implements Runnable {

    // Propieadades.
    int num; // N�mero de fil�sofo.
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
        // El fil�sofo piensa y se alimenta 5 veces.
        for (int i = 0; i < 5; i++) {
            // Primero el fil�sofo piensa.
            pensar();
            // Despu�s se alimenta.
            alimentarse();
        }
        // El fil�sofo se va.
        System.out.println(formateador.format(new Date()) + " --> Fil�sofo "
                + num + ": Abandona la mesa");
    }

    // El fil�sofo se pone a pensar.
    private void pensar() {
        int tiempo = aleatorioPensar.nextInt(5) + 1;
        System.out.println(formateador.format(new Date()) + " --> Fil�sofo "
                + num + ": Piensa durante " + tiempo
                + ((tiempo > 1) ? " segundos" : " segundo"));
        try {
            TimeUnit.SECONDS.sleep(tiempo);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // El fil�sofo quiere alimentarse.
    private void alimentarse() {
        // Coge los palillos.
        mesa.coger(num);
        // Una vez que tiene los palillos puede comer.
        comer();
        // Despu�s de comer suelta los palillos.
        mesa.soltar(num);
    }

    // El fil�sofo se pone a comer.
    private void comer() {
        int tiempo = aleatorioComer.nextInt(5) + 1;
        System.out.println(formateador.format(new Date()) + " --> Fil�sofo "
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
