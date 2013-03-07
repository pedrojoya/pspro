package es.iessaladillo.pedrojoya.pspro.j7cc0601.tarea;

import java.util.concurrent.ConcurrentLinkedDeque;

// Agrega 10000 elementos a la lista.
public class TareaAdicion implements Runnable {

	// Lista que albergará los datos.
	private ConcurrentLinkedDeque<String> lista;

	// Constructor. Recibe la lista.
	public TareaAdicion(ConcurrentLinkedDeque<String> lista) {
		this.lista = lista;
	}

	@Override
	public void run() {
		// Agrego 10000 elementos a la lista.
		String nombreHilo = Thread.currentThread().getName();
		for (int i = 0; i < 10000; i++) {
			lista.add(nombreHilo + ": Elemento " + i);
		}
	}

}
