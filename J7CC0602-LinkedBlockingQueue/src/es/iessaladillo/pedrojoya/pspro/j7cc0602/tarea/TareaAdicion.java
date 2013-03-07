package es.iessaladillo.pedrojoya.pspro.j7cc0602.tarea;

import java.util.Date;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class TareaAdicion implements Runnable {

	// Lista que albergará los datos.
	private LinkedBlockingDeque<String> lista;

	// Constructor. Recibe la lista.
	public TareaAdicion(LinkedBlockingDeque<String> lista) {
		this.lista = lista;
	}

	@Override
	public void run() {
		// Inserto 15 elementos en la lista en 3 ciclos de 5 elementos.
		String elemento = "";
		for (int i = 0; i < 3; i++) {
			System.out.printf("%s -> Ciclo %d de inserción\n", new Date(),
					i + 1);
			for (int j = 0; j < 5; j++) {
				elemento = i + ":" + j;
				try {
					lista.put(elemento);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out
						.printf("%s -> Insertado: %s\n", new Date(), elemento);
			}
			// Duermo dos milisegundos al finalizar cada ciclo.
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}