package es.iessaladillo.pedrojoya.pspro.j7cc0604.tarea;

import java.util.Date;
import java.util.concurrent.DelayQueue;

// Almacena 100 eventos con fecha de activación en la lista.
public class Tarea implements Runnable {

	// Variables miembro.
	private int id;
	private DelayQueue<Evento> lista;

	// Constructor. Recibe el id y la lista.
	public Tarea(int id, DelayQueue<Evento> lista) {
		this.id = id;
		this.lista = lista;
	}

	@Override
	public void run() {
		// La fecha de activación de los 100 eventos será la fecha actual +
		// (1000 milisegundos * id de tarea).
		Date ahora = new Date();
		Date fechaActivacion = new Date();
		fechaActivacion.setTime(ahora.getTime() + (id * 1000));
		// Añado 100 eventos a la lista.
		for (int i = 0; i < 100; i++) {
			lista.add(new Evento("Evento " + id + "-" + i, fechaActivacion));
		}
	}

}
