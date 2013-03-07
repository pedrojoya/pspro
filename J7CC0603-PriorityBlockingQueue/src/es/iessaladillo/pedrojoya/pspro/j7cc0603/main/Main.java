package es.iessaladillo.pedrojoya.pspro.j7cc0603.main;

import java.util.concurrent.PriorityBlockingQueue;

import es.iessaladillo.pedrojoya.pspro.j7cc0603.tarea.Evento;
import es.iessaladillo.pedrojoya.pspro.j7cc0603.tarea.Tarea;

// Ejecuta cinco tareas, cada una de las cuales inserta 1000 eventos en una 
// PriorityBlockingQueue. Al tratarse de una lista ordenada las inserciones 
// se realizan en la posición correcta en base a la prioridad del evento. 
// Finalmente muestra por pantalla todos los eventos en orden ascendente.
public class Main {

	public static void main(String[] args) {
		// Lista ordenada.
		PriorityBlockingQueue<Evento> lista = new PriorityBlockingQueue<>();
		// Creo e inicio cinco hilos para las tareas.
		Thread hilos[] = new Thread[5];
		for (int i = 0; i < hilos.length; i++) {
			hilos[i] = new Thread(new Tarea(i, lista));
		}
		for (int i = 0; i < hilos.length; i++) {
			hilos[i].start();
		}
		// Espero a que los cinco hilos terminen.
		for (int i = 0; i < hilos.length; i++) {
			try {
				hilos[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// Extraigo los eventos desde la cabeza de la lista.
		// Al ser una lista ordenada, se mostrarán de menor a mayor prioridad.
		System.out.printf("Lista ordenada con %d elementos\n", lista.size());
		for (int i = 0; i < hilos.length * 1000; i++) {
			Evento evento = lista.poll();
			System.out.printf("%s -> Prioridad %d\n", evento.getNombre(),
					evento.getPrioridad());
		}
		System.out.printf("Tamaño de la lista tras extracción: %d\n",
				lista.size());
	}

}
