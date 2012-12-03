package es.iessaladillo.pedrojoya.pspro.j7cc0104.main;

import java.util.concurrent.TimeUnit;
import es.iessaladillo.pedrojoya.pspro.j7cc0104.tarea.BuscadorArchivos;

public class Main {

	public static void main(String[] args) {
		// Creo e inicio un hilo buscador para buscar
		// el archivo Readme.txt en C:\ y sus subcarpetas.
		BuscadorArchivos buscador = new BuscadorArchivos("C:\\","Readme.txt");
		Thread hilo = new Thread(buscador);
		hilo.start();
		// Espero 20 segundos.
		try {
			TimeUnit.SECONDS.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// Interrumpo el hilo.
		hilo.interrupt();
	}

}
