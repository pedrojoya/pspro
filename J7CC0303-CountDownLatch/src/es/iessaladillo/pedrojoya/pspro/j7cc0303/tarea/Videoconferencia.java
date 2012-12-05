package es.iessaladillo.pedrojoya.pspro.j7cc0303.tarea;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

// Controlador de la videoconferencia. Usa un CountDownLatch para controlar
// que todos los participantes se han unido a la videoconferencia antes de
// que ésta pueda comenzar.
public class Videoconferencia implements Runnable{

    // Copia local del número de participantes.
    int participantes;
    // Controlador de que se han unido todos los participantes.
    private final CountDownLatch controlador;
    // Formateador de hora.
    SimpleDateFormat formateador = new SimpleDateFormat("HH:mm:ss");
    
    // Constructor. Recibe el número de participantes en la videoconferencia.
    public Videoconferencia(int participantes) {
        // Realizo la copia local del número de participantes.
        this.participantes = participantes;
        // Creo el controlador con una cuenta atrás igual al número de participantes.
        controlador = new CountDownLatch(participantes);
    }

    // Simula la unión de un participante a la videoconferencia. 
    // Recibe el nombre del participante que se une.
    public void unirse(String nombreParticipante) {
        // Informo de que el participantes se la unido a la videoconferencia.
        System.out.printf("%s -> %s conectado\n", 
                formateador.format(new Date()), nombreParticipante);
        // Decremento el contador interno del controlador.
        controlador.countDown();
    }
    
    @Override
    public void run() {
        // Informo del comienzo de la videoconferencia.
        System.out.printf("%s -> Estableciendo conexión con los %d participantes\n",
                formateador.format(new Date()), participantes);
        try {
            // Espero a que se hayan unido todos los participantes.
            controlador.await();
            // Inicio de la videoconferencia.
            System.out.printf("%s -> Todos los participantes conectados. Comenzando...\n",
                    formateador.format(new Date()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
}