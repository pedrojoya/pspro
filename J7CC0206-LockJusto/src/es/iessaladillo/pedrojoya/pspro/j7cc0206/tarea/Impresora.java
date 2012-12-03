package es.iessaladillo.pedrojoya.pspro.j7cc0206.tarea;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Simula la cola de impresi�n de una impresora.
public class Impresora {

    // Creo un lock con MODO JUSTO para controlar la impresi�n en la cola. 
    private final Lock cerrojo = new ReentrantLock(true);
    
    // Imprime el documento que se le pasa como par�metro.
    public void imprimir(Object documento){
        // Hecho el cerrojo.
        cerrojo.lock();
        // Informo de que se ha iniciado la impresi�n.
        SimpleDateFormat formateador = new SimpleDateFormat("HH:mm:ss");
        System.out.printf("%s -> %s: Impresi�n iniciada\n", 
                formateador.format(new Date()),
                Thread.currentThread().getName());
        // Simulo que la impresi�n dura un valor aleatorio de segundos
        // con valor m�ximo de 5 segundos.
        Random aleatorio = new Random();
        try {
            TimeUnit.SECONDS.sleep(aleatorio.nextInt(5));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // Informo de que la impresi�n ha finalizado.
            System.out.printf("%s -> %s: Impresi�n finalizada\n", 
                    formateador.format(new Date()),
                    Thread.currentThread().getName());
            // Libero el cerrojo.
            cerrojo.unlock();
        }
    }
}
