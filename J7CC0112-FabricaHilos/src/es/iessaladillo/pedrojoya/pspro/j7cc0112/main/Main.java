package es.iessaladillo.pedrojoya.pspro.j7cc0112.main;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import es.iessaladillo.pedrojoya.pspro.j7cc0112.fabrica.FabricaHilos;
import es.iessaladillo.pedrojoya.pspro.j7cc0112.tarea.Tarea;

public class Main {

    public static void main(String[] args) {
        // Creo la fábrica de hilos.
        FabricaHilos fabrica = new FabricaHilos("Fábrica");
        SimpleDateFormat formateador = new SimpleDateFormat("HH:mm:ss");
        System.out.printf("%s -> Apertura de la fábrica\n",
                formateador.format(new Date()));
        // Creo la tarea que ejecutarán todos los hilos.
        Tarea tarea = new Tarea();
        // Creo diez hilos a través de la fábrica que ejecutan la misma tarea
        // e inicio su ejecución.
        Thread hilo;
        Random aleatorio = new Random();
        for (int i = 0; i < 10; i++) {
            hilo = fabrica.newThread(tarea);
            hilo.start();
            // Después de iniciar un hilo duermo durante un número de segundos
            // aleatorio con un máximo de 3 segundos.
            try {
                TimeUnit.SECONDS.sleep(aleatorio.nextInt(3));
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        // Muestro el registro (log) de la fábrica.
        System.out.printf("%s\n", fabrica.getRegistro());
    }

}
