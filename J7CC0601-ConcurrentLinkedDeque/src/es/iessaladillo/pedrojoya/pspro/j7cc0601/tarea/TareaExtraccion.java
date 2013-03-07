package es.iessaladillo.pedrojoya.pspro.j7cc0601.tarea;

import java.util.concurrent.ConcurrentLinkedDeque;

// Extrae 10000 elementos de la lista.
public class TareaExtraccion implements Runnable {

	// Lista que alberga los datos.
	private ConcurrentLinkedDeque<String> lista;

	// Constructor. Recibe la lista.
	public TareaExtraccion(ConcurrentLinkedDeque<String> lista) {
		this.lista = lista;
	}

	@Override
	public void run() {
		// Extraigo 10000 elementos (5000 del principio y 5000 del final).
		for (int i = 0; i < 5000; i++) {
			lista.pollFirst();
			lista.pollLast();
		}
	}

}
