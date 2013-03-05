package ejercicio1;

// ESTÁ MUY BIEN Y ELEGANTE. SÓLO HA QUEDADO UN POCO
// FEO LA CREACIÓN DEL ARRAY EN Pizarra.java

public class Principal {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		// Recurso compartido
		Pizarra p = new Pizarra();
		
		// Creamos los hilos
		Tirador hilo1 = new Tirador(p);
		Tirador hilo2 = new Tirador(p);
		Tirador hilo3 = new Tirador(p);
		
		// Inicializamos los hilos
		hilo1.start();
		hilo2.start();
		hilo3.start();
		
		// Esperamos a que todos acaben para sacar los resultados
		hilo1.join();
		hilo2.join();
		hilo3.join();
		
		p.mostrarResultados();
	}

}
