package es.iessaladillo.pedrojoya.pspro.j7cc0501.tarea;

import java.util.ArrayList;
import java.util.List;

// Genera una lista de productos de un determinado tama�o.
// Cada producto se inicializa con un nombre y precio predefinido.
public class Generador {

	// Retorna una lista de productos del tama�o pasado como par�metro.
	public List<Producto> generar(int cuantos) {
		// Creo el ArrayList.
		List<Producto> lista = new ArrayList<Producto>();
		// A�ado los productos inicializados con precio 10�.
		for (int i = 0; i < cuantos; i++) {
			lista.add(new Producto("Producto " + i, 10));
		}
		// Retorno la lista.
		return lista;
	}

}
