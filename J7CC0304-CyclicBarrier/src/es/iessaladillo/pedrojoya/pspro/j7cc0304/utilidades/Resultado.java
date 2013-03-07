package es.iessaladillo.pedrojoya.pspro.j7cc0304.utilidades;

// Se usa para almacenar en un array el n�mero de ocurrencias del n�mero
// buscado en cada una de las filas de la matriz.
public class Resultado {

	// Array de ocurrencias en las filas.
	private int ocurrenciasFila[];

	// Constructor. Recibe el n�mero de filas de la matriz.
	public Resultado(int filas) {
		ocurrenciasFila = new int[filas];
	}

	// Establece el n�mero de ocurrencias de una fila.
	public void setOcurrencias(int fila, int valor) {
		ocurrenciasFila[fila] = valor;
	}

	// Retorna los resultados.
	public int[] getResultados() {
		return ocurrenciasFila;
	}
}
