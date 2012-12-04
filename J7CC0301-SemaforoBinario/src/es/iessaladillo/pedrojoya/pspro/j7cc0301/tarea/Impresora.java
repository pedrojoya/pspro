package es.iessaladillo.pedrojoya.pspro.j7cc0301.tarea;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

// Simula la cola de impresi�n de una impresora.
public class Impresora {

    // Creo un sem�foro binario para controlar la impresi�n en la cola. 
    private final Semaphore semaforo = new Semaphore(1);
    
    // Imprime el documento que se le pasa como par�metro.
    public void imprimir(Object documento){
        SimpleDateFormat formateador = new SimpleDateFormat("HH:mm:ss");
        try {
            // Adquiero el sem�foro.
            semaforo.acquire();
            // Informo de que se ha iniciado la impresi�n.
            System.out.printf("%s -> %s: Impresi�n iniciada\n", 
                    formateador.format(new Date()),
                    Thread.currentThread().getName());
            // Simulo que la impresi�n dura un valor aleatorio de segundos
            // con valor m�ximo de 5 segundos.
            Random aleatorio = new Random();
            TimeUnit.SECONDS.sleep(aleatorio.nextInt(5));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // Informo de que la impresi�n ha finalizado.
            System.out.printf("%s -> %s: Impresi�n finalizada\n", 
                    formateador.format(new Date()),
                    Thread.currentThread().getName());
            // Libero el sem�foro.
            semaforo.release();
        }
    }
}
