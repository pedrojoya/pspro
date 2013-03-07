package es.iessaladillo.pedrojoya.pspro.j7cc0302.tarea;

// Simula un trabajo que env�a un documento a imprimir.
public class Trabajo implements Runnable {

	// Cola de impresi�n.
	private ColaImpresion colaImpresion;

	// Constructor.
	public Trabajo(ColaImpresion colaImpresion) {
		this.colaImpresion = colaImpresion;
	}

	@Override
	public void run() {
		// Mando a imprimir un documento.
		colaImpresion.imprimir(new Object());
	}
}
