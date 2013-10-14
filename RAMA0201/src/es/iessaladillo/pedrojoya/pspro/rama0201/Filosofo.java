package es.iessaladillo.pedrojoya.pspro.rama0201;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Filosofo implements Runnable {

    // Propieadades.
    private int num; // Número de filósofo.
    private Palillo izda; // Palillo izquierdo.
    private Palillo dcha; // Palillo derecho.

    // Auxiliares.
    SimpleDateFormat formateador = new SimpleDateFormat("HH:mm:ss");
    Random aleatorioPensar = new Random();
    Random aleatorioComer = new Random();

    // Constructor.
    public Filosofo(int num, Palillo izda, Palillo dcha) {
        this.num = num;
        this.izda = izda;
        this.dcha = dcha;
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

    // El filósofo decide alimentarse.
    private void alimentarse() {
        // Si el filósofo con número par coge primero el palillo de la
        // derecha y si es un filósofo con número impar coge primero el de la
        // izquierda.
        if (num == 0 || num % 2 == 0) {
            // Coje el palillo derecho y después el izquierdo.
            dcha.coger(num);
            izda.coger(num);
            // Una vez que tiene los palillos puede comer.
            comer();
            // Después de comer suelta los palillos.
            dcha.soltar(num);
            izda.soltar(num);
        } else {
            // Coje el palillo izquierdo y después el derecho.
            izda.coger(num);
            dcha.coger(num);
            // Una vez que tiene los palillos puede comer.
            comer();
            // Después de comer suelta los palillos.
            izda.soltar(num);
            dcha.soltar(num);
        }
    }

    // El filósofo come.
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
