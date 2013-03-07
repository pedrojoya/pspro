package es.iessaladillo.pedrojoya.pspro.j7cc0411.tarea;

import java.util.concurrent.TimeUnit;

// Simula una tarea esperando un tiempo aleatorio.
public class Tarea implements Runnable {

	private String nombre;

	// Constructor. Recibe el nombre de la tarea.
	public Tarea(String name) {
		this.nombre = name;
	}

	@Override
	public void run() {
		// Duermo un número de segundos aleatorios.
		try {
			Long duracion = (long) (Math.random() * 10);
			System.out.printf("Ejecutando %s durante %d segundos\n", nombre,
					duracion);
			TimeUnit.SECONDS.sleep(duracion);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("%s finalizada\n", nombre);
	}

	// Retorna el nombre de la tarea.
	public String toString() {
		return nombre;
	}

}