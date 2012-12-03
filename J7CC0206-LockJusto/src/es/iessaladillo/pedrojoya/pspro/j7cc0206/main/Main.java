package es.iessaladillo.pedrojoya.pspro.j7cc0206.main;

import es.iessaladillo.pedrojoya.pspro.j7cc0206.tarea.Impresora;
import es.iessaladillo.pedrojoya.pspro.j7cc0206.tarea.Trabajo;

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
        // lance la ejecución de todos con un intervalo
        // de 1 décima entre uno y otro, para dar tiempo
        // a que soliciten el cerrojo en orden.
        for (int i = 0; i < 10; i++){
            hilos[i].start();
            try { 
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
    }

}
