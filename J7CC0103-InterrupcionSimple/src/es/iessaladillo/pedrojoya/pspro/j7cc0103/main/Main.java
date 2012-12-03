package es.iessaladillo.pedrojoya.pspro.j7cc0103.main;

import es.iessaladillo.pedrojoya.pspro.j7cc0103.tarea.GeneradorPrimos;

public class Main {

	public static void main(String[] args) {
		// Creo e inicio el hilo generador de números primos.
		Thread hilo = new GeneradorPrimos();
		hilo.start();
		// Espero 3 segundos.
		try {
			Thread.sleep(3000);
			// TimeUnit.SECONDS.sleep(3); // Alternativa.
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// Marco el hilo como interrumpido.
		hilo.interrupt();
	}

}
