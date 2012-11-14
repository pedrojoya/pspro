package es.iessaladillo.pedrojoya.psp05;

import java.util.ArrayList;

public class Almacen {

	// Variables miembro.
	ArrayList lista = new ArrayList();
	
	public void meter(Object elemento) {
		// Intento 1.
		lista.add(elemento);
	}

	public Object sacar() {
		// Intento 1: Falla data-races meter() y sacar() a la vez.
		// Espero hasta que haya algo en la lista.
		while (lista.isEmpty()) ;
		// Retorno el primer elemento de la lista (cola FIFO).
		return lista.remove(0);
	}

}
