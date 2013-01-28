package es.iessaladillo.pedrojoya.pspro.j7cc0601.main;

import java.util.concurrent.ConcurrentLinkedDeque;

import es.iessaladillo.pedrojoya.pspro.j7cc0601.tarea.TareaAdicion;
import es.iessaladillo.pedrojoya.pspro.j7cc0601.tarea.TareaExtraccion;

// Ejecuta 100 tareas de adici�n para a�adir 1000000 de elementos a la lista.
// Luego ejecuta 100 tareas de extracci�n para eliminar todos los elementos.
public class Main {

    public static void main(String[] args) throws Exception {
        // Creo la lista.
        ConcurrentLinkedDeque<String> lista = new ConcurrentLinkedDeque<>();
        // Creo e inicio 100 tareas de adici�n.
        Thread hilos[] = new Thread[100];
        for (int i = 0; i < hilos.length; i++) {
            hilos[i] = new Thread(new TareaAdicion(lista));
            hilos[i].start();
        }
        // Espero la finalizaci�n de los hilos.
        for (int i = 0; i < hilos.length; i++) {
            hilos[i].join();
        }
        // Informo del tama�o de la lista tras todas la adiciones.
        System.out.printf("Tama�o de la lista tras adiciones: %d\n",
                lista.size());
        // Creo e inicio 100 tareas de extracci�n.
        for (int i = 0; i < hilos.length; i++) {
            hilos[i] = new Thread(new TareaExtraccion(lista));
            hilos[i].start();
        }
        // Espero la finalizaci�n de los hilos.
        for (int i = 0; i < hilos.length; i++) {
            hilos[i].join();
        }
        // Informo del tama�o de la lista tras todas las extracciones.
        System.out.printf("Tama�o de la lista tras extracciones: %d\n",
                lista.size());
    }

}