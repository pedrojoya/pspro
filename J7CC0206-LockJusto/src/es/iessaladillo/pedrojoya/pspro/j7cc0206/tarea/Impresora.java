package es.iessaladillo.pedrojoya.pspro.j7cc0206.tarea;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Simula la cola de impresión de una impresora.
public class Impresora {

    // Creo un lock con MODO JUSTO para controlar la impresión en la cola. 
    private final Lock cerrojo = new ReentrantLock(true);
    
    // Imprime el documento que se le pasa como parámetro.
    public void imprimir(Object documento){
        // Hecho el cerrojo.
        cerrojo.lock();
        // Informo de que se ha iniciado la impresión.
        SimpleDateFormat formateador = new SimpleDateFormat("HH:mm:ss");
        System.out.printf("%s -> %s: Impresión iniciada\n", 
                formateador.format(new Date()),
                Thread.currentThread().getName());
        // Simulo que la impresión dura un valor aleatorio de segundos
        // con valor máximo de 5 segundos.
        Random aleatorio = new Random();
        try {
            TimeUnit.SECONDS.sleep(aleatorio.nextInt(5));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // Informo de que la impresión ha finalizado.
            System.out.printf("%s -> %s: Impresión finalizada\n", 
                    formateador.format(new Date()),
                    Thread.currentThread().getName());
            // Libero el cerrojo.
            cerrojo.unlock();
        }
    }
}
