package es.iessaladillo.pedrojoya.pspro.j7cc0101.main;

import es.iessaladillo.pedrojoya.pspro.j7cc0101.tarea.Calculadora;

public class Main {

	public static void main(String[] args) {
		// Inicio 10 hilos que realizan el cálculo con números diferentes.
		for (int i=1; i<=10; i++){
			// Creo el objeto calculadora.
			Calculadora calculadora = new Calculadora(i);
			// Creo un hilo que ejecute el objeto calculadora.
			Thread thread = new Thread(calculadora);
			// Inicio la ejecución del hilo.
			thread.start();
		}
	}
	
}
