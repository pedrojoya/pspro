package es.iessaladillo.pedrojoya.pspro.j7cc0608.main;

import java.util.concurrent.atomic.AtomicIntegerArray;

import es.iessaladillo.pedrojoya.pspro.j7cc0608.tarea.Decremento;
import es.iessaladillo.pedrojoya.pspro.j7cc0608.tarea.Incremento;

// Ejecuta 100 hilos que incrementan todos los valores del array
// y 100 hilos que decrementan todos los valores del array.
public class Main {

	public static void main(String[] args) {
		// Constante de número de hilos.
		final int NUM_HILOS = 100;
		// Creo un array atómico de 1000 enteros.
		AtomicIntegerArray array = new AtomicIntegerArray(1000);
		// Creo una tarea de incremento y una de decremento.
		Incremento incremento = new Incremento(array);
		Decremento decremento = new Decremento(array);
		// Creo e inicio 100 hilos de incremento y 100 de decremento.
		Thread hilosIncremento[] = new Thread[NUM_HILOS];
		Thread hilosDecremento[] = new Thread[NUM_HILOS];
		for (int i = 0; i < NUM_HILOS; i++) {
			hilosIncremento[i] = new Thread(incremento);
			hilosDecremento[i] = new Thread(decremento);
			hilosIncremento[i].start();
			hilosDecremento[i].start();
		}
		// Espero la finalización de todos los hilos.
		for (int i = 0; i < NUM_HILOS; i++) {
			try {
				hilosIncremento[i].join();
				hilosDecremento[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// Escribo los valores diferentes a 0 del array tras los cambios.
		int valor;
		int contador = 0;
		for (int i = 0; i < array.length(); i++) {
			valor = array.get(i);
			if (valor != 0) {
				contador++;
				System.out.println("Array[" + i + "]: " + valor);
			}
		}
		System.out.printf("Elementos distintos de 0: %d\n", contador);
	}

}