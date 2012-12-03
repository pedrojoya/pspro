package es.iessaladillo.pedrojoya.pspro.j7cc0102.tarea;

//Imprime la tabla de multiplicar de un n�mero.
public class Calculadora implements Runnable {

	private int numero;	// N�mero base.
	
	// Constructor.
	public Calculadora(int numero) {
		this.numero = numero;
	}
	
	// M�todo que ejecuta el hilo.
	@Override
	public void run() {
		// Muestro la tabla de multiplicar del n�mero.
		for (int i=1; i<=10; i++){
			System.out.printf("%s: %d * %d = %d\n", Thread.currentThread().getName(), numero, i, i * numero);
		}
	}

}
