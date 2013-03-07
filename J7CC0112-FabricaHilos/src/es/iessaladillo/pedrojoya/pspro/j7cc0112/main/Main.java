package es.iessaladillo.pedrojoya.pspro.j7cc0112.main;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import es.iessaladillo.pedrojoya.pspro.j7cc0112.fabrica.FabricaHilos;
import es.iessaladillo.pedrojoya.pspro.j7cc0112.tarea.Tarea;

public class Main {

    public static void main(String[] args) {
        // Creo la f�brica de hilos.
        FabricaHilos fabrica = new FabricaHilos("F�brica");
        SimpleDateFormat formateador = new SimpleDateFormat("HH:mm:ss");
        System.out.printf("%s -> Apertura de la f�brica\n",
                formateador.format(new Date()));
        // Creo la tarea que ejecutar�n todos los hilos.
        Tarea tarea = new Tarea();
        // Creo diez hilos a trav�s de la f�brica que ejecutan la misma tarea
        // e inicio su ejecuci�n.
        Thread hilo;
        Random aleatorio = new Random();
        for (int i = 0; i < 10; i++) {
            hilo = fabrica.newThread(tarea);
            hilo.start();
            // Despu�s de iniciar un hilo duermo durante un n�mero de segundos
            // aleatorio con un m�ximo de 3 segundos.
            try {
                TimeUnit.SECONDS.sleep(aleatorio.nextInt(3));
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        // Muestro el registro (log) de la f�brica.
        System.out.printf("%s\n", fabrica.getRegistro());
    }

}
