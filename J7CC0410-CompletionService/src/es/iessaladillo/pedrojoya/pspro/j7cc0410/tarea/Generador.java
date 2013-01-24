package es.iessaladillo.pedrojoya.pspro.j7cc0410.tarea;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

// Simula la generaci�n de un objeto. Se ejecutar� en un CompletionService
public class Generador implements Callable<String> {

    private String nombreSolicitante;
    private String titulo;

    // Constructor. Recibe el nombre del solicitante y el t�tulo del informe.
    public Generador(String nombreSolicitante, String titulo) {
        this.nombreSolicitante = nombreSolicitante;
        this.titulo = titulo;
    }

    // Retorna una cadena con el texto del informe.
    @Override
    public String call() throws Exception {
        // Simula la generaci�n del informe esperando un tiempo aleatorio.
        try {
            Long duracion = (long) (Math.random() * 10);
            System.out.printf(
                    "Generador -> %s para %s creado en %d segundos\n",
                    this.titulo, this.nombreSolicitante, duracion);
            TimeUnit.SECONDS.sleep(duracion);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String informe = titulo + " para " + nombreSolicitante;
        return informe;
    }

}