package es.iessaladillo.pedrojoya.pspro.j7cc0305.tarea;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class Tarea implements Runnable {

    // Variables a nivel de clase.
    Phaser secuenciador;
    SimpleDateFormat formateador = new SimpleDateFormat("HH:mm:ss");
    Random aleatorio = new Random();

    // Constructor.
    public Tarea(Phaser secuenciador) {
        this.secuenciador = secuenciador;
    }

    @Override
    public void run() {
        // Fase iniciada y finalizada: Iniciarse.
        // Se espera a que todos estén disponibles para empezar a la vez.
        secuenciador.arriveAndAwaitAdvance();
        // Se realizan los cuatro trabajos, esperando cuando termino cada uno de
        // ellos a que el resto haya concluído ese mismo trabajo.
        // Se realiza el primer trabajo.
        for (int i = 0; i < 4; i++) {
            // Fase iniciada. Se informa.
            System.out.println(formateador.format(new Date()) + " --> "
                    + Thread.currentThread().getName() + " inicia trabajo "
                    + (i + 1));
            trabajar();
            // Fase finalizada. Se informa.
            System.out.println(formateador.format(new Date()) + " --> "
                    + Thread.currentThread().getName() + " finaliza trabajo "
                    + (i + 1));
            // Se indica al secuenciador que hemos terminado una fase.
            secuenciador.arriveAndAwaitAdvance();
        }
        // Al terminar todas las fases el hilo se desvincula del secuenciador.
        secuenciador.arriveAndDeregister();
        System.out.println(formateador.format(new Date()) + " --> "
                + Thread.currentThread().getName()
                + " se desvincula del secuenciador");
    }

    private void trabajar() {
        // Se simula el trabajo durmiendo durante como máximo 5 segundos.
        try {
            TimeUnit.SECONDS.sleep(aleatorio.nextInt(6));
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}