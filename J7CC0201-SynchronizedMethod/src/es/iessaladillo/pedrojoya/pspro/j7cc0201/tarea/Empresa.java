package es.iessaladillo.pedrojoya.pspro.j7cc0201.tarea;

// Empresa.
public class Empresa implements Runnable {

	// Propiedades.
	private Cuenta cuenta;
	
	// Constructor
	public Empresa(Cuenta cuenta) {
		this.cuenta = cuenta;
	}
	
	public void run() {
		// Realizo 10 ingresos de 10 euros.
		for (int i = 0; i < 10; i++){
			cuenta.ingresar(10);
		}
	}

}
