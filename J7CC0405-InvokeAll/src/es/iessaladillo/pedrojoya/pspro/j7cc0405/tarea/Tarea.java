package es.iessaladillo.pedrojoya.pspro.j7cc0405.tarea;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

// Tarea. Como resultado calcula la suma de cinco n�mero aleatorios.
public class Tarea implements Callable<Resultado> {

	private String nombre;
	private Random aleatorio = new Random();

	// Constructor.
	public Tarea(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public Resultado call() throws Exception {
		// Simulo la duraci�n de la tarea.
		try {
			int duracion = aleatorio.nextInt(10);
			System.out.printf("%s -> Trabajando %d segundos.\n", nombre,
					duracion);
			TimeUnit.SECONDS.sleep(duracion);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// Calculo la suma de 5 n�meros aleatorios.
		int valor = 0;
		for (int i = 0; i < 5; i++) {
			valor += aleatorio.nextInt(100);

		}
		// Retorno un objeto Resultado.
		return new Resultado(nombre, valor);
	}

}
