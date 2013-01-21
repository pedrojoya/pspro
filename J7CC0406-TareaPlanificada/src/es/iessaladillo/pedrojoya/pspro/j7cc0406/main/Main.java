package es.iessaladillo.pedrojoya.pspro.j7cc0406.main;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import es.iessaladillo.pedrojoya.pspro.j7cc0406.tarea.Tarea;

public class Main {

    public static void main(String[] args) {
        // Creo un ejecutor para tareas planificadas con 1 hilo
        // (ScheduledThreadPoolExecutor)
        ScheduledThreadPoolExecutor ejecutor = (ScheduledThreadPoolExecutor) Executors
                .newScheduledThreadPool(1);
        // Env�o cinco tareas al ejecutor para que sean ejecutadas con un
        // cierto retardo respecto a cuando son enviadas:
        // Tarea 0: 1 segundo, Tarea 1: 2 segundos, Tarea 2: 3 segundos, etc.
        for (int i = 0; i < 5; i++) {
            // Creo la tarea
            Tarea tarea = new Tarea("Tarea " + i);
            // Informo del env�o de la tarea.
            System.out.printf("%s -> Enviada en: %s\n", "Tarea " + i,
                    new Date());
            // Env�o la tarea planificando su retardo.
            ejecutor.schedule(tarea, i + 1, TimeUnit.SECONDS);
        }
        // Finalizo el ejecutor.
        ejecutor.shutdown();
        // Espero la finalizaci�n de las tareas una vez finalizado el ejecutor.
        try {
            ejecutor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Informo de que todas las tareas han finalizado
        System.out.printf("Todas las tareas finalizadas en: %s\n", new Date());
    }

}