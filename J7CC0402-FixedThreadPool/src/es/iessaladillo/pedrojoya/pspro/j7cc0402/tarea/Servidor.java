package es.iessaladillo.pedrojoya.pspro.j7cc0402.tarea;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

//Simula un servidor web que recibe peticiones y usa un ThreadPoolExecutor
//de tipo CachedThreadPool para ejecutarlas. 
public class Servidor {

    // Ejecutor de las tareas.
    private ThreadPoolExecutor ejecutor;

    // Constructor
    public Servidor() {
        // Creo el ejecutor del tipo FixedThreadPool con 5 hilos.
        ejecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
    }

    // Ejecuta una tarea. Recibe la tarea a ejecutar.
    public void ejecutarTarea(Tarea tarea) {
        // Ejecuto un hilo para la tarea.
        ejecutor.execute(tarea);
        // Informo de los datos del ejecutor.
        System.out.printf("Servidor -> Tamaño Grupo: %d\n",
                ejecutor.getPoolSize());
        System.out.printf("Servidor -> Hilos activos: %d\n",
                ejecutor.getActiveCount());
    }

    // Cierra el servidor.
    public void apagarServidor() {
        // Finalizo el ejecutor.
        ejecutor.shutdown();
    }

}
