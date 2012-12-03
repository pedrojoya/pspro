package es.iessaladillo.pedrojoya.psp04;

class Trabajador {
	
	// Contador.
	private int contador;

	// Clase interna de hilo trabajador.
	class HiloTrabajador implements Runnable {
		
		int id;

		public HiloTrabajador(int id) {
			this.id = id;
		}

		@Override
		public void run() {
			// Incremento 10.000 veces el contador.
			for (int i = 0; i < 10000; i++) {
				incrementar();
			}		
		}
		
	}

	// Trabajar.
	public void trabajar() {
		
		// Creo dos hilos que incrementarán el contador.
		Thread hilo1 = new Thread(new HiloTrabajador(1));
		Thread hilo2 = new Thread(new HiloTrabajador(2));
		
		// Inicio la ejecución de los dos hilos.
		hilo1.start();
		hilo2.start();
		// **** Si quitamos todo el bloque try la salida puede ser inmediata y mostrar 0.
		try {
			hilo1.join();
			hilo2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// **** Fin 
		
		// Muestro el valor del contador.
		System.out.println("Contador: " + contador);
	}

	// **** Si no fuera sincronizado las condiciones de carrera devolverían un resultado erróneo.
	private synchronized void incrementar() {
		contador++;
	}
	// **** Fin
}
public class App {
	
	public static void main(String[] args) {
		Trabajador trabajador = new Trabajador();
		trabajador.trabajar();
	}

}
