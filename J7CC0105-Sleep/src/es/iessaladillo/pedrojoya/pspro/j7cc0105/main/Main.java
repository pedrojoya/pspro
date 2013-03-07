package es.iessaladillo.pedrojoya.pspro.j7cc0105.main;

import java.util.concurrent.TimeUnit;

import es.iessaladillo.pedrojoya.pspro.j7cc0105.tarea.Segundero;

public class Main {

    public static void main(String[] args) {
        // Creo un hilo que ejecute el segundero y lo inicio.
        Segundero segundero = new Segundero();
        Thread hilo = new Thread(segundero);
        hilo.start();
        // Espero cinco segundos antes de interrumpirlo.
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Marco como interrumpido el hilo del segundero.
        hilo.interrupt();
    }
}
