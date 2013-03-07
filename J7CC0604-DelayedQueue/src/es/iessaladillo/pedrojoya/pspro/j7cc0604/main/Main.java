package es.iessaladillo.pedrojoya.pspro.j7cc0604.main;

import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

import es.iessaladillo.pedrojoya.pspro.j7cc0604.tarea.Evento;
import es.iessaladillo.pedrojoya.pspro.j7cc0604.tarea.Tarea;

// Ejecuta cinco tareas en sus respectivos hilos. Cada tarea inserta en una
// lista 100 eventos con fecha de activación. Una vez finalizadas las tareas
// muestra todos los eventos.
public class Main {

	public static void main(String[] args) throws Exception {
		// Lista de elementos con fecha de activación.
		DelayQueue<Evento> lista = new DelayQueue<>();
		// Creo e inicio cinco tareas.
		Thread hilos[] = new Thread[5];
		for (int i = 0; i < hilos.length; i++) {
			hilos[i] = new Thread(new Tarea(i + 1, lista));
		}
		for (int i = 0; i < hilos.length; i++) {
			hilos[i].start();
		}
		// Espero la finalización de todas las tareas.
		for (int i = 0; i < hilos.length; i++) {
			hilos[i].join();
		}
		// Extraigo los eventos de la lista. Los que están disponibles en un
		// momento dado es porque se ha pasado su fecha de activación.
		// Mientras queden elementos en la lista tengo que continuar el
		// proceso.
		System.out.printf("Lista con %d elementos\n", lista.size());
		int pasadas = 0;
		int numElementos;
		Evento evento;
		do {
			numElementos = 0;
			System.out.printf("Pasada %d\n", pasadas);
			do {
				evento = lista.poll();
				if (evento != null) {
					System.out.printf("%s. Activación: %s\n",
							evento.getNombre(), evento.getActivacion());
					numElementos++;
				}
			} while (evento != null);
			pasadas++;
			System.out.printf("%s -> %d elementos mostrados\n", new Date(),
					numElementos);
			// Duermo un segundo.
			TimeUnit.MILLISECONDS.sleep(1000);
		} while (lista.size() > 0);
	}
}
