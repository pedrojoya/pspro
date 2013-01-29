package es.iessaladillo.pedrojoya.pspro.j7cc0606.main;

import es.iessaladillo.pedrojoya.pspro.j7cc0606.tarea.Tarea;

public class Main {

    public static void main(String[] args) {
        // Creo tres tareas en sus correspondientes hilos.
        Thread hilos[] = new Thread[3];
        for (int i = 0; i < hilos.length; i++) {
            hilos[i] = new Thread(new Tarea());
            hilos[i].start();
        }
    }

}
