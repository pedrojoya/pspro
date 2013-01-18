package es.iessaladillo.pedrojoya.pspro.j7cc0402.tarea;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

// Simula una tarea que debe ejecutar el servidor.
public class Tarea implements Runnable {

    // Nombre de la tarea
    private String nombre;
    // Generador de n�meros aleatorios para simular la duraci�n de la tarea.
    Random aleatorio = new Random();
    // Formateador de hora.
    SimpleDateFormat formateador = new SimpleDateFormat("HH:mm:ss");
    
    // Constructor. Recibe el nombre de la tarea.
    public Tarea(String nombre) {
        this.nombre = nombre;
    }
    
    @Override
    public void run() {
        // Informo sobre el momento de inicio.
        System.out.printf("%s -> %s -> Iniciada en: %s\n", Thread.currentThread().getName(), nombre, formateador.format(new Date()));
        // Simulo la duraci�n de la ejecuci�n de la tarea.
        try {
            int duracion = aleatorio.nextInt(10);
            TimeUnit.SECONDS.sleep(duracion);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Informo sobre el momento de finalizaci�n
        System.out.printf("%s -> %s -> Finalizada en: %s\n", Thread.currentThread().getName(), nombre, formateador.format(new Date()));
    }

}
