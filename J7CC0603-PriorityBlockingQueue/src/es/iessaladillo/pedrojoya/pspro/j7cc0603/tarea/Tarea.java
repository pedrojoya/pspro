package es.iessaladillo.pedrojoya.pspro.j7cc0603.tarea;

import java.util.concurrent.PriorityBlockingQueue;

// Genera 1000 eventos y los almacena en la lista ordenada.
public class Tarea implements Runnable {

	// Variable miembro.
	private int id;
	private PriorityBlockingQueue<Evento> lista;

	// Constructor. Recibe el id y la lista.
	public Tarea(int id, PriorityBlockingQueue<Evento> lista) {
		this.id = id;
		this.lista = lista;
	}

	@Override
	public void run() {
		// Creo 1000 eventos con su prioridad y los añado a la lista ordenada.
		for (int i = 0; i < 1000; i++) {
			lista.add(new Evento("Evento " + id + "-" + i, 999 - i));
		}
	}
}
