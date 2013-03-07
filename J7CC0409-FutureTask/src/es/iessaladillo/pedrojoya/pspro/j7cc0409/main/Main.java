package es.iessaladillo.pedrojoya.pspro.j7cc0409.main;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import es.iessaladillo.pedrojoya.pspro.j7cc0409.tarea.Tarea;
import es.iessaladillo.pedrojoya.pspro.j7cc0409.tarea.TareaResultado;

// Crea 5 tareas, espera 5 segundos, las cancela y escribe el resultado de las
// tareas que ya habían terminado de ejecutarse.
public class Main {

	public static void main(String[] args) {
		// Creo el ejecutor
		ExecutorService ejecutor = (ExecutorService) Executors
				.newCachedThreadPool();
		// Para cada tarea real, creo una tarea de resultado que es la que envío
		// al ejecutor.
		TareaResultado tareasResultado[] = new TareaResultado[5];
		for (int i = 0; i < 5; i++) {
			tareasResultado[i] = new TareaResultado(new Tarea("Tarea " + i));
			ejecutor.submit(tareasResultado[i]);
		}
		// Espero 5 segundos.
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		// Cancelo todas las tareas. Si las tareas ya han finalizado su
		// ejecución, no les afecta.
		for (int i = 0; i < tareasResultado.length; i++) {
			tareasResultado[i].cancel(true);
		}
		// Escribo el resultado de las tareas que finalizaron su ejecución
		// antes de la cancelación.
		for (int i = 0; i < tareasResultado.length; i++) {
			try {
				if (!tareasResultado[i].isCancelled()) {
					System.out.printf("%s\n", tareasResultado[i].get());
				}
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		// Finalizo el ejecutor.
		ejecutor.shutdown();
	}

}
