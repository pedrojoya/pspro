package es.iessaladillo.pedrojoya.pspro.j7cc0302.main;

import es.iessaladillo.pedrojoya.pspro.j7cc0302.tarea.Trabajo;
import es.iessaladillo.pedrojoya.pspro.j7cc0302.tarea.ColaImpresion;

public class Main {

	public static void main (String args[]){
		// Creo la cola de impresión
		ColaImpresion colaImpresion = new ColaImpresion();
		// Creo doce hilos para los trabajos de impresión.
		Thread hilos[] = new Thread[12];
		for (int i = 0; i < 12; i++){
			hilos[i] = new Thread(new Trabajo(colaImpresion), "Trabajo " + i);
		}
		// Inicio los hilos.
		for (int i = 0; i < 12; i++){
			hilos[i].start();
		}
	}

}
