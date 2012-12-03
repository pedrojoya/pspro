package es.iessaladillo.pedrojoya.pspro.j7cc0204.main;

import es.iessaladillo.pedrojoya.pspro.j7cc0204.tarea.Trabajo;
import es.iessaladillo.pedrojoya.pspro.j7cc0204.tarea.Impresora;

public class Main {

    public static void main (String args[]){
        // Creo una nueva impresora.
        Impresora impresora = new Impresora();
        // Creo e inicio 10 hilos que ejecutan 10 trabajos.
        Thread hilos[]= new Thread[10];
        for (int i = 0; i < 10; i++){
            hilos[i] = new Thread(new Trabajo(impresora), "Trabajo " + i);
            hilos[i].start();
        }
    }

}
