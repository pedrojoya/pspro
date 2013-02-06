package es.iessaladillo.pedrojoya.pspro.j7cc0501.main;

import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

import es.iessaladillo.pedrojoya.pspro.j7cc0501.tarea.Producto;
import es.iessaladillo.pedrojoya.pspro.j7cc0501.tarea.Generador;
import es.iessaladillo.pedrojoya.pspro.j7cc0501.tarea.Tarea;

// Crea una lista de productos, un ejecutor ForkJoinPool y la tarea.
public class Main {

	public static void main(String[] args) {
		// Creo la lista de productos a través de una clase generadora.
		Generador generador = new Generador();
		List<Producto> productos = generador.generar(10000);
		// Creo la tarea actualizadora de precios.
		Tarea tarea = new Tarea(productos, 0, productos.size(), 0.20);
		// Creo el ejecutor.
		ForkJoinPool ejecutor = new ForkJoinPool();
		// Envío la tarea al ejecutor.
		ejecutor.execute(tarea);
		// Muestro información sobre el ejecutor hasta que la tarea haya
		// concluido.
		do {
//			System.out.printf("Número de hilos: %d\n",
//					ejecutor.getActiveThreadCount());
//			System.out.printf("Robo de hilos: %d\n", ejecutor.getStealCount());
//			System.out.printf("Paralelismo: %d\n", ejecutor.getParallelism());
			// Duermo 5 milésimas.
			try {
				TimeUnit.MILLISECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (!tarea.isDone());
		// Finalizo el ejecutor.
		ejecutor.shutdown();
		// Compruebo que la tarea ha finalizado normalmente.
		if (tarea.isCompletedNormally()) {
			System.out.printf("La tarea ha finalizado normalmente\n");
		}
		// Compruebo que todos los productos se han actualizado.
		for (int i = 0; i < productos.size(); i++) {
			Producto producto = productos.get(i);
			if (producto.getPrecio() != 12) {
				System.out.printf("Producto %s: %f\n", producto.getNombre(),
						producto.getPrecio());
			}
		}
	}

}