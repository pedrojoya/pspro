package es.iessaladillo.pedrojoya.pspro.j7cc0307.tarea;

import java.util.List;
import java.util.concurrent.Exchanger;

public class Consumidor implements Runnable {

	// Buffer en el que se obtendr�n los datos producidos
	// por el productor.
	private List<String> buffer;

	// Intercambiador para sincronizar con el productor
	// e intercambiar el buffer.
	private final Exchanger<List<String>> intercambiador;

	// Constructor. Recibe el el buffer que va a usar y el intercambiador
	// (com�n a productor y consumidor).
	public Consumidor(List<String> buffer,
			Exchanger<List<String>> intercambiador) {
		this.buffer = buffer;
		this.intercambiador = intercambiador;
	}

	// El consumidor tendr� 10 ciclos de consumo y en cada uno de ellos
	// consumir� 10 datos.
	@Override
	public void run() {
		// Por cada ciclo.
		for (int i = 0; i < 10; i++) {
			// Informo del inicio del ciclo de consumo.
			System.out.printf("Consumidor -> Ciclo %d\n", i + 1);
			// Espero a que el productor haya producido los 10 datos del ciclo
			// y se pueda realizar el intercambio.
			try {
				// El consumidor recibe a trav�s de intercambiador el buffer
				// del productor con 10 datos y le env�a su buffer vac�o.
				System.out.printf("Consumidor -> Intercambio\n");
				buffer = intercambiador.exchange(buffer);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// Informo del tama�o del buffer despu�s del intercambio.
			System.out.printf("Consumidor -> Tama�o del buffer: %d\n",
					buffer.size());
			// Consumo los 10 datos del buffer correspondiente al ciclo.
			// Por lo que finalmente queda vac�o.
			for (int j = 0; j < 10; j++) {
				String dato = buffer.get(0);
				System.out.printf("Consumidor -> Consumido %s\n", dato);
				buffer.remove(0);
			}
		}
	}

}