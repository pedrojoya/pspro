package es.iessaladillo.pedrojoya.pspro.j7cc0410.main;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import es.iessaladillo.pedrojoya.pspro.j7cc0410.tarea.Receptor;
import es.iessaladillo.pedrojoya.pspro.j7cc0410.tarea.Solicitante;

public class Main {

    public static void main(String[] args) {
        // Creo el ejecutor.
        ExecutorService ejecutor = (ExecutorService) Executors
                .newCachedThreadPool();
        // Creo el CompletionService y le paso al constructor el ejecutor.
        CompletionService<String> servicio = new ExecutorCompletionService<>(
                ejecutor);
        // Creo los dos hilos solicitantes de un informe.
        Thread sol1 = new Thread(new Solicitante("Baldomero", servicio));
        Thread sol2 = new Thread(new Solicitante("Genaro", servicio));
        // Creo un hilo para ejecutar el receptor de los informes.
        Receptor r = new Receptor(servicio);
        Thread receptor = new Thread(r);
        // Inicio los hilos.
        sol1.start();
        sol2.start();
        receptor.start();
        // Espero a la finalización de los hilos solicitantes de informes.
        try {
            sol1.join();
            sol2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Finalizo el ejecutor
        ejecutor.shutdown();
        // Espero la finalización de todas las tareas del ejecutor.
        try {
            ejecutor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Finalizo la ejecución del procesador de informes.
        r.setFinalizar(true);
    }

}
