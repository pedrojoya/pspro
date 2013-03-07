package es.iessaladillo.pedrojoya.pspro.j7cc0302.tarea;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Simula una cola de impresi�n �nica con tres impresoras.
public class ColaImpresion {

	// Constantes.
	final int NUM_IMPRESORAS = 3;

	// Sem�foro para controlar cuantos trabajos de impresi�n
	// puede haber a la vez
	private Semaphore semaforo;
	// Array de booleanos para indicar si cada impresora est� disponible.
	private boolean disponible[];
	// Cerrojo para controlar el acceso al array de disponibilidad.
	private Lock cerrojoDisponibles;
	// Generador de n�meros aleatorios.
	Random aleatorio = new Random();

	// Constructor.
	public ColaImpresion() {
		// El sem�foro permitir� tres trabajos de impresi�n simult�neos.
		semaforo = new Semaphore(NUM_IMPRESORAS);
		// Inicializo el array de disponibilidad de impresoras.
		disponible = new boolean[NUM_IMPRESORAS];
		for (int i = 0; i < NUM_IMPRESORAS; i++) {
			disponible[i] = true;
		}
		// Creo el cerrojo para el array de disponibilidad.
		cerrojoDisponibles = new ReentrantLock();
	}

	// Imprime un documento.
	public void imprimir(Object documento) {
		try {
			// Obtengo el sem�foro.
			semaforo.acquire();
			// Obtengo el n�mero de impresora en la que imprimir.
			int impresora = getImpresora();
			// Simulo la impresi�n del documento.
			int duracion = aleatorio.nextInt(10);
			// Informo de inicio de impresi�n.
			System.out.printf("Impresora %d: Iniciando %s\n", impresora, Thread
					.currentThread().getName());
			TimeUnit.SECONDS.sleep(duracion);
			// Libero la impresora.
			disponible[impresora] = true;
			// Informo de fin de impresi�n.
			System.out.printf("Impresora %d: Finalizado %s\n", impresora,
					Thread.currentThread().getName());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			// Libero el sem�foro.
			semaforo.release();
		}
	}

	// Obtiene la primera impresora que est� disponible o -1 si no hay ninguna.
	private int getImpresora() {
		// Impresora.
		int impresora = -1;
		try {
			// Obtengo el cerrojo de sincronizaci�n para el array.
			cerrojoDisponibles.lock();
			// Recorro el array y obtengo la primera impresora disponible.
			for (int i = 0; i < disponible.length; i++) {
				if (disponible[i]) {
					impresora = i;
					// La pongo como no disponible.
					disponible[i] = false;
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Free the access to the array
			cerrojoDisponibles.unlock();
		}
		return impresora;
	}

}
