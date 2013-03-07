package es.iessaladillo.pedrojoya.pspro.j7cc0410.tarea;

import java.util.concurrent.CompletionService;

// Simula un solicitante de un informe
public class Solicitante implements Runnable {

	private String nombre;

	// CompletionService usado para la ejecuci�n de las tareas de
	// generaci�n de informes
	private CompletionService<String> servicio;

	// Constructor. Recibe el nombre del solicitante y el servicio.
	public Solicitante(String nombre, CompletionService<String> servicio) {
		this.nombre = nombre;
		this.servicio = servicio;
	}

	@Override
	public void run() {
		// Creo el generador de informes y lo env�o al servicio.
		String titulo = "Historial";
		Generador generador = new Generador(nombre, titulo);
		System.out.printf("Solicitante -> %s solicita %s\n", nombre, titulo);
		servicio.submit(generador);
	}

}