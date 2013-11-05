package es.iessaladillo.pedrojoya.pspro.j7cc0306.main;

import es.iessaladillo.pedrojoya.pspro.j7cc0306.tarea.Estudiante;
import es.iessaladillo.pedrojoya.pspro.j7cc0306.tarea.Secuenciador;

public class Main {

    public static void main(String[] args) {
        // Se crea el array de hilos.
        Thread[] hilos = new Thread[5];
        // Se crea un objeto Secuenciador que sincronice cinco hilos.
        Secuenciador secuenciador = new Secuenciador(5);
        // Se crean cinco hilos y se inician.
        for (int i = 0; i < 5; i++) {
            hilos[i] = new Thread(new Estudiante(secuenciador), "Estudiante "
                    + (i + 1));
            hilos[i].start();
        }
        // Se espera la finalización de todos los hilos.
        for (int i = 0; i < 5; i++) {
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