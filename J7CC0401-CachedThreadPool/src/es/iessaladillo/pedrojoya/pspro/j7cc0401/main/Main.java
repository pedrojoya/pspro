package es.iessaladillo.pedrojoya.pspro.j7cc0401.main;

import es.iessaladillo.pedrojoya.pspro.j7cc0401.tarea.Servidor;
import es.iessaladillo.pedrojoya.pspro.j7cc0401.tarea.Tarea;

public class Main {

	public static void main(String[] args) {
		// Creo el servidor
		Servidor servidor = new Servidor();
		// Env�o 50 peticiones al servidor y finalizo.
		for (int i = 0; i < 50; i++) {
			Tarea tarea = new Tarea("Tarea " + i);
			servidor.ejecutarTarea(tarea);
			try {
				// Cuanto menos esperemos m�s tareas llegar�n a la vez,
				// y el ejecutor crear� m�s hilos y viceversa.
				// Probar con distintos valores de espera.
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// Finalizo el servidor
		servidor.apagarServidor();
	}

}
