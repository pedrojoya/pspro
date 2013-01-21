package es.iessaladillo.pedrojoya.pspro.j7cc0407.main;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import es.iessaladillo.pedrojoya.pspro.j7cc0407.tarea.Tarea;

public class Main {

    public static void main(String[] args) {
        // Creo un servicio de ejecuci�n ScheduledExecutorService con un hilo.
        ScheduledExecutorService ejecutor = Executors.newScheduledThreadPool(1);
        // Creo una nueva tarea y la env�o al ejecutar para que se ejecute con
        // un retardo inicial de 1 segundo y posteriormente se ejecute cada 2
        // segundos.
        // Como resultado obtengo un objeto SheduledFuture que me permite
        // controlar ciertos aspectos de la planificaci�n.
        // Como la tarea implementa Runnable y no Callable y por tanto no
        // retorna nada, el par�metro de ScheduledFuture tiene que ser ?
        Tarea tarea = new Tarea("Tarea");
        ScheduledFuture<?> resultado = ejecutor.scheduleAtFixedRate(tarea, 1,
                2, TimeUnit.SECONDS);
        // Cada cierto tiempo compruebo cu�nto queda para la pr�xima ejecuci�n
        // de la tarea.
        for (int i = 0; i < 10; i++) {
            System.out.printf("Pr�xima ejecuci�n dentro de %d milisegundos\n",
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
