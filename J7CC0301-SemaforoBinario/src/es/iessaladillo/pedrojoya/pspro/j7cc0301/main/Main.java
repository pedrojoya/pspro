package es.iessaladillo.pedrojoya.pspro.j7cc0301.main;

import es.iessaladillo.pedrojoya.pspro.j7cc0301.tarea.Impresora;
import es.iessaladillo.pedrojoya.pspro.j7cc0301.tarea.Trabajo;

public class Main {

    public static void main (String args[]){
        // Creo una nueva impresora.
        Impresora impresora = new Impresora();
        // Creo e inicio 10 hilos que ejecutan 10 trabajos.
        Thread hilos[]= new Thread[10];
        for (int i = 0; i < 10; i++){
            hilos[i] = new Thread(new Trabajo(impresora), "Trabajo " + i);
        }
        // Lo hago en dos bucles distintos para que se
        // lance la ejecuci�n de todos a la vez.
        for (int i = 0; i < 10; i++){
            hilos[i].start();
        }
    }

}