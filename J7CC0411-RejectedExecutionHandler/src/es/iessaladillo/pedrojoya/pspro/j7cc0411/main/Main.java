package es.iessaladillo.pedrojoya.pspro.j7cc0411.main;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import es.iessaladillo.pedrojoya.pspro.j7cc0411.tarea.GestorTareasRechazadas;
import es.iessaladillo.pedrojoya.pspro.j7cc0411.tarea.Tarea;

public class Main {

	public static void main(String[] args) {
		// Creo el gestor de tareas rechazadas.
		GestorTareasRechazadas gestor = new GestorTareasRechazadas();
		// Creo el ejecutor.
		ThreadPoolExecutor ejecutor = (ThreadPoolExecutor) Executors
				.newCachedThreadPool();
		// Asigno al ejecutor el gestor de tareas rechazadas.
		ejecutor.setRejectedExecutionHandler(gestor);
		// Lanzo tres tareas y se las envío al ejecutor.
		System.out.printf("Enviando tres tareas al ejecutor...\n");
		for (int i = 0; i < 3; i++) {
			Tarea tarea = new Tarea("Tarea " + i);
			ejecutor.submit(tarea);
		}
		// Apago el ejecutor
		System.out.printf("Apagando el ejecutor...\n");
		ejecutor.shutdown();
		// Envío otra tarea (que será rechazada).
		System.out.printf("Enviando una cuarta tarea al ejecutor...\n");
		ejecutor.submit(new Tarea("Tarea 4"));
	}

}
