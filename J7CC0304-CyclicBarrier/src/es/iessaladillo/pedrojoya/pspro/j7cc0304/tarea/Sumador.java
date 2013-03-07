package es.iessaladillo.pedrojoya.pspro.j7cc0304.tarea;

import es.iessaladillo.pedrojoya.pspro.j7cc0304.utilidades.Resultado;

// Suma los valores almacenados en el objeto Resultado, correspondientes a un
// array con el n�mero de ocurrencias por fila.
public class Sumador implements Runnable {

	// Resultados: N�mero de ocurrencias por fila.
	private Resultado resultado;

	// Constructor. Recibe el resultado.
	public Sumador(Resultado results) {
		this.resultado = results;
	}

	@Override
	public void run() {
		// Informo al usuario de que se est�n procesando los resultados.
		System.out.printf("Procesando resultado final...\n");
		// Recorro el array de resultado sumando todos los valores.
		int suma = 0;
		int datos[] = resultado.getResultados();
		for (int valor : datos) {
			suma += valor;
		}
		// Informo al usuario del resultado final.
		System.out.printf("N�mero total de ocurrencias: %d.\n", suma);
	}

}
