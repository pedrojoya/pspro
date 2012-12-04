package es.iessaladillo.pedrojoya.pspro.j7cc0301.tarea;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

// Simula la cola de impresión de una impresora.
public class Impresora {

    // Creo un semáforo binario para controlar la impresión en la cola. 
    private final Semaphore semaforo = new Semaphore(1);
    
    // Imprime el documento que se le pasa como parámetro.
    public void imprimir(Object documento){
        SimpleDateFormat formateador = new SimpleDateFormat("HH:mm:ss");
        try {
            // Adquiero el semáforo.
            semaforo.acquire();
            // Informo de que se ha iniciado la impresión.
            System.out.printf("%s -> %s: Impresión iniciada\n", 
                    formateador.format(new Date()),
                    Thread.currentThread().getName());
            // Simulo que la impresión dura un valor aleatorio de segundos
            // con valor máximo de 5 segundos.
            Random aleatorio = new Random();
            TimeUnit.SECONDS.sleep(aleatorio.nextInt(5));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // Informo de que la impresión ha finalizado.
            System.out.printf("%s -> %s: Impresión finalizada\n", 
                    formateador.format(new Date()),
                    Thread.currentThread().getName());
            // Libero el semáforo.
            semaforo.release();
        }
    }
}
