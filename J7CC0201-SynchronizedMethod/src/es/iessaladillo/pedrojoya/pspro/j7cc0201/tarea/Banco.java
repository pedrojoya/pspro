package es.iessaladillo.pedrojoya.pspro.j7cc0201.tarea;

// Banco
public class Banco implements Runnable {

	// Propiedades.
	private Cuenta cuenta;

	// Constructor. Recibe la cuenta manejada.
	public Banco(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public void run() {
		// Realizo 10 cargos de 5 euros
		for (int i = 0; i < 10; i++) {
			cuenta.cargar(5);
		}
	}

}
