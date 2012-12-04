package es.iessaladillo.pedrojoya.pspro.j7cc0302.tarea;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This class implements a PrintQueue that have access to three printers.
 * 
 * We use a Semaphore to control the access to one of the printers. When
 * a job wants to print, if there is one or more printers free, it has access
 * to one of the free printers. If not, it sleeps until one of the printers
 * is free.
 *
 */
// Simula una cola de impresión única con tres impresoras.
public class ColaImpresion {
	
	// Constantes.
	final int NUM_IMPRESORAS = 3;
	
	// Semáforo para controlar cuantos trabajos de impresión
	// puede haber a la vez
	private Semaphore semaforo;
	// Array de booleanos para indicar si cada impresora está disponible.
	private boolean disponibles[];
	// Cerrojo para controlar el acceso al array de disponibilidad.
	private Lock cerrojoDisponibles;
	
	// Constructor.
	public ColaImpresion(){
		// El semáforo permitirá tres trabajos de impresión simultáneos.
		semaforo = new Semaphore(NUM_IMPRESORAS);
		// Inicializo el array de disponibilidad de impresoras.
		disponibles = new boolean[NUM_IMPRESORAS];
		for (int i = 0; i < NUM_IMPRESORAS; i++){
			disponibles[i] = true;
		}
		// Creo el cerrojo para el array de disponibilidad.
		cerrojoDisponibles = new ReentrantLock();
	}
	
	// Imprime un documento.
	public void imprimir(Object documento){
		try {
			// Obtengo el semáforo.
			semaforo.acquire();
			// Obtengo el número de impresora en la que imprimir.
			int impresora = getImpresora();
			// Simulo la impresión del documento.
			Long duration=(long)(Math.random()*10);
			System.out.printf("%s: PrintQueue: Printing a Job in Printer %d during %d seconds\n",Thread.currentThread().getName(),impresora,duration);
			TimeUnit.SECONDS.sleep(duration);
			
			// Free the printer
			disponibles[impresora]=true;
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			// Free the semaphore
			semaforo.release();			
		}
	}

	private int getImpresora() {
		int ret=-1;
		
		try {
			// Get the access to the array
			cerrojoDisponibles.lock();
			// Look for the first free printer
			for (int i=0; i<disponibles.length; i++) {
				if (disponibles[i]){
					ret=i;
					disponibles[i]=false;
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Free the access to the array
			cerrojoDisponibles.unlock();
		}
		return ret;
	}

}
