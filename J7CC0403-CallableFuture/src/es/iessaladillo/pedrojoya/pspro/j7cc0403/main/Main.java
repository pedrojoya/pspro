package es.iessaladillo.pedrojoya.pspro.j7cc0403.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import es.iessaladillo.pedrojoya.pspro.j7cc0403.tarea.CalculadoraFactorial;

public class Main {

	public static void main(String[] args) {
		// Creo un FixedThreadPool de dos hilos.
		ThreadPoolExecutor ejecutor = (ThreadPoolExecutor) Executors
				.newFixedThreadPool(2);
		// Creo una lista en la que almacenar los objetos Future con el
		// resultado
		// de los hilos.
		List<Future<Integer>> listaResultados = new ArrayList<>();
		// Creo 10 tareas consistente en el c�lculo del factorial de n�meros
		// aleatorios menores que 10.
		Random aleatorio = new Random();
		Integer[] numeros = new Integer[10];
		for (int i = 0; i < 10; i++) {
			numeros[i] = new Integer(aleatorio.nextInt(10));
			CalculadoraFactorial factorial = new CalculadoraFactorial(
					numeros[i]);
			Future<Integer> futuroResultado = ejecutor.submit(factorial);
			listaResultados.add(futuroResultado);
		}
		// Espero la finalizaci�n de las diez tareas.
		do {
			// Mientras espero muestro los objetos Future disponibles.
			System.out.printf("Completas -> %d. Tareas:",
					ejecutor.getCompletedTaskCount());
			for (int i = 0; i < listaResultados.size(); i++) {
				if (listaResultados.get(i).isDone()) {
					System.out.printf(" %d", i);
				}
			}
			System.out.printf("\n");
			// Duermo durante unas mil�simas para volver a comprobar.
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (ejecutor.getCompletedTaskCount() < listaResultados.size());
		// Muestro los resultados
		System.out.printf("Resultados:\n");
		Integer valor = null;
		for (int i = 0; i < listaResultados.size(); i++) {
			// Obtengo el objeto future de la lista.
			Future<Integer> objetoResultado = listaResultados.get(i);
			try {
				valor = objetoResultado.get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
			System.out.printf("Tarea %d -> Factorial de %d = %d\n", i,
					numeros[i], valor);
		}
		// Finalizo el ejecutor.
		ejecutor.shutdown();
	}

}