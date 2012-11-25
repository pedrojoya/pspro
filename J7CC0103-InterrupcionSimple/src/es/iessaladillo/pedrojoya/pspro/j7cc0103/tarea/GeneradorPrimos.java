package es.iessaladillo.pedrojoya.pspro.j7cc0103.tarea;

public class GeneradorPrimos extends Thread {

	@Override
	public void run() {
		// Variables.
		long number=1L;	// N�mero inicial.
		// Bucle infinito.
		while (true) {
			// Si el n�mero es primo lo muestro.
			if (esPrimo(number)) {
				System.out.printf("%d es primo\n", number);
			}
			// Compruebo si he sido marcado como interrumpido
			// y si es as� retorno del m�todo.
			if (isInterrupted()) {
				System.out.printf("Se ha interrumpido el hilo\n");
				return;
			}
			// Incremento el n�mero.
			number++;
		}
	}

	// Retorna si un n�mero es primo o no.
	private boolean esPrimo(long numero) {
		if (numero <= 2) {
			return true;
		}
		for (long i = 2; i < numero; i++) {
			if ((numero % i) == 0) {
				return false;
			}
		}
		return true;
	}

}
