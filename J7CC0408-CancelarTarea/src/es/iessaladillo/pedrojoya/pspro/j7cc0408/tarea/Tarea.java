package es.iessaladillo.pedrojoya.pspro.j7cc0408.tarea;

import java.util.concurrent.Callable;

// Simula una tarea consistente en escribir un mensaje por pantalla cada 100 milisegundos.
public class Tarea implements Callable<String> {

    @Override
    public String call() throws Exception {
        while (true) {
            System.out.printf("Ejecutando la tarea...\n");
            Thread.sleep(100);
        }
    }

}
