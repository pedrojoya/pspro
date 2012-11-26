package es.iessaladillo.pedrojoya.pspro.j7cc0110.tarea;

import java.util.Random;
import java.util.concurrent.TimeUnit;

// Simula una operación de búsqueda
public class Buscador implements Runnable {

	private Resultado resultado; // Resultado de la búsqueda.
	
	// Constructor. Recibe el objeto donde se almacenará el resultado.
	public Buscador(Resultado resultado) {
		this.resultado = resultado;
	}

	@Override
	public void run() {
		String nombreHilo = Thread.currentThread().getName();
		System.out.printf("Hilo %s: Comenzando búsqueda\n", nombreHilo);
		try {
			buscar();
			// Establezco el resultado como el nombre del hilo.
			resultado.setName(nombreHilo);
		} catch (InterruptedException e) {
			// Si el hilo es interrumpido mientras duerme se produce
			// esta excepción, que aprovecho para finalizar el hilo.
			System.out.printf("Hilo %s: Ha sido interrumpido\n", nombreHilo);
			// Finalizo la ejecución del hilo.
			return;
		}
		// Informo de que este hilo ha finalizado la búsqueda.
		System.out.printf("Hilo %s: Búsqueda finalizada\n", nombreHilo);
	}
	
	/**
	 * Simula la operación de búsqueda durmiendo durante un número aleatorio 
	 * de segundos (como máximo 30)
	 * @throws InterruptedException Throws this exception if the Thread is interrupted
	 */
	private void buscar() throws InterruptedException {
		// Obtengo aleatoriamente el número de segundos que va a dormir el hilo.
		Random aleatorio = new Random();
		int segundos = aleatorio.nextInt(30);
		// Informo al usuario y duermo.
		System.out.printf("Hilo %s: La búsqueda tardará %d segundos\n",Thread.currentThread().getName(), segundos);
		// Si es interrumpido mientras duerme lanza la excepción InterruptedException.
		TimeUnit.SECONDS.sleep(segundos);
	}

}
