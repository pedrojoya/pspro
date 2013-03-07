package es.iessaladillo.pedrojoya.pspro.j7cc0501.tarea;

import java.util.List;
import java.util.concurrent.RecursiveAction;

// Modela una tarea que actualiza el precio de los productos de una lista.
// Si tiene que actualizar 10 o más productos, divide el trabajo en dos
// subtareas y las ejecuta.
public class Tarea extends RecursiveAction {

	// SerialVersionUID (identificador). Requerido por la intefaz serializable
	// implementada por la clase ForkJoinTask y sus subclases.
	private static final long serialVersionUID = 1L;

	// Variables miembro.
	private List<Producto> productos; // Lista de productos.
	private int desde; // Índice de la lista desde el que debe trabajar.
	private int hasta; // Índice final de la lista de trabajo (no incluido).
	private double incremento; // Incremento del precio (en tanto por 1).

	// Constructor. Recibe la lista, el intervalo de trabajo (desde, hasta)
	// y el incremento.
	public Tarea(List<Producto> productos, int desde, int hasta,
			double incremento) {
		this.productos = productos;
		this.desde = desde;
		this.hasta = hasta;
		this.incremento = incremento;
	}

	// Método principal de RecursiveAction.
	@Override
	protected void compute() {
		// Si debo actualizar menos de 10 productos los actualizo.
		if (hasta - desde < 10) {
			actualizarPrecios();
		} else {
			// Parto por la mitad la lista.
			int mitad = (hasta + desde) / 2;
			// System.out.printf("Tareas pendientes: %s\n",
			// getQueuedTaskCount());
			System.out.printf("[%d,%d) descompuesta en [%d,%d) y [%d,%d)\n",
					desde, hasta, desde, mitad + 1, mitad + 1, hasta);
			// Creo dos subtareas asignándoles la mitad de los índices.
			Tarea subtarea1 = new Tarea(productos, desde, mitad + 1, incremento);
			Tarea subtarea2 = new Tarea(productos, mitad + 1, hasta, incremento);
			// Llamo a las dos subtareas y espero que terminen (síncrona).
			invokeAll(subtarea1, subtarea2);
		}
	}

	// Actualiza los precios de la lista.
	private void actualizarPrecios() {
		// Recorro la lista incrementado el precio de los productos.
		for (int i = desde; i < hasta; i++) {
			Producto producto = productos.get(i);
			producto.setPrecio(producto.getPrecio() * (1 + incremento));
		}
	}

}
