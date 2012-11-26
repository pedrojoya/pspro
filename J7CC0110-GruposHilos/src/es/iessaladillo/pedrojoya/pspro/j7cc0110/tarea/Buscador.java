package es.iessaladillo.pedrojoya.pspro.j7cc0110.tarea;

import java.util.Random;
import java.util.concurrent.TimeUnit;

// Simula una operaci�n de b�squeda
public class Buscador implements Runnable {

	private Resultado resultado; // Resultado de la b�squeda.
	
	// Constructor. Recibe el objeto donde se almacenar� el resultado.
	public Buscador(Resultado resultado) {
		this.resultado = resultado;
	}

	@Override
	public void run() {
		String nombreHilo = Thread.currentThread().getName();
		System.out.printf("Hilo %s: Comenzando b�squeda\n", nombreHilo);
		try {
			buscar();
			// Establezco el resultado como el nombre del hilo.
			resultado.setName(nombreHilo);
		} catch (InterruptedException e) {
			// Si el hilo es interrumpido mientras duerme se produce
			// esta excepci�n, que aprovecho para finalizar el hilo.
			System.out.printf("Hilo %s: Ha sido interrumpido\n", nombreHilo);
			// Finalizo la ejecuci�n del hilo.
			return;
		}
		// Informo de que este hilo ha finalizado la b�squeda.
		System.out.printf("Hilo %s: B�squeda finalizada\n", nombreHilo);
	}
	
	/**
	 * Simula la operaci�n de b�squeda durmiendo durante un n�mero aleatorio 
	 * de segundos (como m�ximo 30)
	 * @throws InterruptedException Throws this exception if the Thread is interrupted
	 */
	private void buscar() throws InterruptedException {
		// Obtengo aleatoriamente el n�mero de segundos que va a dormir el hilo.
		Random aleatorio = new Random();
		int segundos = aleatorio.nextInt(30);
		// Informo al usuario y duermo.
		System.out.printf("Hilo %s: La b�squeda tardar� %d segundos\n",Thread.currentThread().getName(), segundos);
		// Si es interrumpido mientras duerme lanza la excepci�n InterruptedException.
		TimeUnit.SECONDS.sleep(segundos);
	}

}
