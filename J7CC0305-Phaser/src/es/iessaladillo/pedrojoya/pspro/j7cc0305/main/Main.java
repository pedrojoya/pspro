package es.iessaladillo.pedrojoya.pspro.j7cc0305.main;

import java.util.concurrent.Phaser;

import es.iessaladillo.pedrojoya.pspro.j7cc0305.tarea.Tarea;

public class Main {

    public static void main(String[] args) {
        // Se crea el array de hilos.
        Thread[] hilos = new Thread[3];
        // Se crea un objeto Phaser que sincronice tres hilos.
        Phaser secuenciador = new Phaser(3);
        // Se crean tres hilos y se inician.
        for (int i = 0; i < 3; i++) {
            hilos[i] = new Thread(new Tarea(secuenciador), "Trabajador "
                    + (i + 1));
            hilos[i].start();
        }
        // Se espera la finalización de todos los hilos.
        for (int i = 0; i < 3; i++) {
            try {
                hilos[i].join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        // Se muestra si el secuenciador ha terminado.
        System.out.println("¿Secuenciador terminado? "
                + secuenciador.isTerminated());
    }

}
