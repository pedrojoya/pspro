package es.iessaladillo.pedrojoya.pspro.j7cc0404.tarea;

import java.util.Random;
import java.util.concurrent.TimeUnit;

// Simula un sistema de validaci�n
public class SistemaValidacion {

	private String nombreSistema;

	// Constructor.
	public SistemaValidacion(String nombreSistema) {
		this.nombreSistema = nombreSistema;
	}

	// Simula la validaci�n del usuario.
	// Recibe el nombre de usuario y la contrase�a.
	public boolean validar(String name, String password) {
		// Simulo la duraci�n de la validaci�n.
		Random aleatorio = new Random();
		try {
			int duracion = aleatorio.nextInt(5);
			System.out.printf("%s -> Duraci�n: %d segundos\n", nombreSistema,
					duracion);
			TimeUnit.SECONDS.sleep(duracion);
		} catch (InterruptedException e) {
			return false;
		}
		// Simulo el resultado de la validaci�n.
		return aleatorio.nextBoolean();
	}

	// Retorna el nombre del sistema de validaci�n.
	public String getName() {
		return nombreSistema;
	}

}