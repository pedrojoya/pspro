package es.iessaladillo.pedrojoya.pspro.j7cc0501.tarea;

import java.util.ArrayList;
import java.util.List;

// Genera una lista de productos de un determinado tamaño.
// Cada producto se inicializa con un nombre y precio predefinido.
public class Generador {

	// Retorna una lista de productos del tamaño pasado como parámetro.
	public List<Producto> generar(int cuantos) {
		// Creo el ArrayList.
		List<Producto> lista = new ArrayList<Producto>();
		// Añado los productos inicializados con precio 10€.
		for (int i = 0; i < cuantos; i++) {
			lista.add(new Producto("Producto " + i, 10));
		}
		// Retorno la lista.
		return lista;
	}

}
