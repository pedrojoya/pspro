package es.iessaladillo.pedrojoya.pspro.j7cc0408.main;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import es.iessaladillo.pedrojoya.pspro.j7cc0408.tarea.Tarea;

// Creo una tarea, espera dos segundos y la cancela.
public class Main {

    public static void main(String[] args) {
        // Creo un ejecutor.
        ThreadPoolExecutor ejecutor = (ThreadPoolExecutor) Executors
                .newCachedThreadPool();
        // Envío una tarea al ejecutor.
        Future<String> resultado = ejecutor.submit(new Tarea());
        // Duermo durante dos segundos.
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Cancelo la tarea.
        System.out.printf("Cancelando la tarea...\n");
        resultado.cancel(true);
        // Verifico que la tarea ha sido cancelada
        System.out.printf("Tarea cancelada? %s\n", resultado.isCancelled());
        System.out.printf("Tarea terminada? %s\n", resultado.isDone());
        // Finalizo el ejecutor.
        ejecutor.shutdown();
    }

}
