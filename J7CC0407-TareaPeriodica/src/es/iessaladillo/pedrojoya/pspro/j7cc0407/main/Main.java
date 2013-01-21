package es.iessaladillo.pedrojoya.pspro.j7cc0407.main;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import es.iessaladillo.pedrojoya.pspro.j7cc0407.tarea.Tarea;

public class Main {

    public static void main(String[] args) {
        // Creo un servicio de ejecución ScheduledExecutorService con un hilo.
        ScheduledExecutorService ejecutor = Executors.newScheduledThreadPool(1);
        // Creo una nueva tarea y la envío al ejecutar para que se ejecute con
        // un retardo inicial de 1 segundo y posteriormente se ejecute cada 2
        // segundos.
        // Como resultado obtengo un objeto SheduledFuture que me permite
        // controlar ciertos aspectos de la planificación.
        // Como la tarea implementa Runnable y no Callable y por tanto no
        // retorna nada, el parámetro de ScheduledFuture tiene que ser ?
        Tarea tarea = new Tarea("Tarea");
        ScheduledFuture<?> resultado = ejecutor.scheduleAtFixedRate(tarea, 1,
                2, TimeUnit.SECONDS);
        // Cada cierto tiempo compruebo cuánto queda para la próxima ejecución
        // de la tarea.
        for (int i = 0; i < 10; i++) {
            System.out.printf("Próxima ejecución dentro de %d milisegundos\n",
                    resultado.getDelay(TimeUnit.MILLISECONDS));
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Finalizo el ejecutor.
        ejecutor.shutdown();
    }

}
