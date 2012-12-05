package es.iessaladillo.pedrojoya.pspro.j7cc0303.tarea;

import java.util.Random;
import java.util.concurrent.TimeUnit;

// Simula un participante en una videoconferencia.
public class Participante implements Runnable {

    // Videoconferencia.
    private Videoconferencia videoconferencia;
    // Nombre del participante.
    private String nombre;
    // Generador de números aleatorios para simular la conexión.
    Random aleatorio = new Random();
    
    // Constructor. Recibe la videoconferencia y el nombre del participante.
    public Participante(Videoconferencia videoconferencia, String nombre) {
        this.videoconferencia = videoconferencia;
        this.nombre = nombre;
    }

    @Override
    public void run() {
        // Simulo la conexión al sistema de videoconferencia.
        int duracion = aleatorio.nextInt(10);
        try {
            TimeUnit.SECONDS.sleep(duracion);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Me uno a la videoconferencia
        videoconferencia.unirse(nombre);
    }
    
}