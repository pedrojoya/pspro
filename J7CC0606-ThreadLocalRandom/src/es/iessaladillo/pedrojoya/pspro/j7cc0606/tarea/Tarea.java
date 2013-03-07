package es.iessaladillo.pedrojoya.pspro.j7cc0606.tarea;

import java.util.concurrent.ThreadLocalRandom;

// Genera números aleatorios en relación a todos los hilos.
public class Tarea implements Runnable {

	// Constructor.
	public Tarea() {
		// Obtengo el objeto generador de números aleatorios para este hilo.
		ThreadLocalRandom.current();
	}

	@Override
	public void run() {
		// Muestro por pantalla 10 números aleatorios (del 0 al 49).
		String nombre = Thread.currentThread().getName();
		for (int i = 0; i < 10; i++) {
			System.out.printf("%s: %d\n", nombre, ThreadLocalRandom.current()
					.nextInt(50));
		}
	}

}
