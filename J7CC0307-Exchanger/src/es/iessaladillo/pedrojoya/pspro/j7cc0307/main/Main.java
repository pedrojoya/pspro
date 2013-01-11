package es.iessaladillo.pedrojoya.pspro.j7cc0307.main;

import es.iessaladillo.pedrojoya.pspro.j7cc0307.tarea.Consumidor;
import es.iessaladillo.pedrojoya.pspro.j7cc0307.tarea.Productor;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

public class Main {

	public static void main(String[] args) {
		// Buffers: estructuras de datos que serán intercambiadas.
		List<String> buffer1 = new ArrayList<>();
		List<String> buffer2 = new ArrayList<>();
		// Objeto Exchanger intercambiador de ese tipo de estructuras de datos.
		// que compartirán tanto Productor como Consumidor.
		Exchanger<List<String>> intercambiador = new Exchanger<>();
		// Creo e inicio los hilos del productor y del consumidor.
		Thread hiloProductor = new Thread(new Productor(buffer1, intercambiador));
		Thread hiloConsumidor = new Thread(new Consumidor(buffer2, intercambiador));
		hiloProductor.start();
		hiloConsumidor.start();
	}

}