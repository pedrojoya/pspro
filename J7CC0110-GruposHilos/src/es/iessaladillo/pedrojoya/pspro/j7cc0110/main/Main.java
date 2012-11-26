package es.iessaladillo.pedrojoya.pspro.j7cc0110.main;

import java.util.concurrent.TimeUnit;

import es.iessaladillo.pedrojoya.pspro.j7cc0110.tarea.Buscador;
import es.iessaladillo.pedrojoya.pspro.j7cc0110.tarea.Resultado;

public class Main {

	public static void main(String[] args) {
		// Creo un nuevo grupo de hilos llamado Buscadores.
		ThreadGroup grupoBuscadores = new ThreadGroup("Buscadores");
		Resultado result=new Resultado();
		// Creo un objeto buscador.
		Buscador buscador = new Buscador(result);
		// Creo e inicio cinco hilos que ejecutan el mismo objeto buscador
		// añadiéndolo al grupo de hilo Buscadores.
		for (int i = 0; i < 10; i++) {
			Thread hilo = new Thread(grupoBuscadores, buscador);
			hilo.start();
			// Espero 1 segundo entre la creación de un hilo y la siguiente.
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// Muestro información sobre el grupo de hilo
		System.out.printf("Buscadores -> Número de hilos: %d\n", grupoBuscadores.activeCount());
		System.out.printf("Buscadores -> Listado\n");
		grupoBuscadores.list();
		// Obtengo el array de hilos activos del grupo y muestro información sobre el estado
		// de cada uno de ellos.
		Thread[] hilos = new Thread[grupoBuscadores.activeCount()];
		grupoBuscadores.enumerate(hilos);
		for (int i=0; i< grupoBuscadores.activeCount(); i++) {
			System.out.printf("%s: %s\n", hilos[i].getName(), hilos[i].getState());
		}
		// Espero la finalización de todos los hilos del grupo.
		esperarHiloGrupo(grupoBuscadores);
		// Interrumpo todos los hilos del grupos.
		grupoBuscadores.interrupt();
	}

	/**
	 * Espera la finalización de algún hilo del grupo
	 * @param grupoHilos
	 */
	private static void esperarHiloGrupo(ThreadGroup grupoHilos) {
		// Mientras todos los hilos del grupo estén activos
		// me duermo durante un segundo.
		while (grupoHilos.activeCount() > 9) {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
