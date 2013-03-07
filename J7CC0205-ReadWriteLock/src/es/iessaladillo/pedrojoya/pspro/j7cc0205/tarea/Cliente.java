package es.iessaladillo.pedrojoya.pspro.j7cc0205.tarea;

// Cliente que consulta el precio varias veces.
public class Cliente implements Runnable {

	// Propiedades.
	private Producto producto;

	// Constructor.
	public Cliente(Producto producto) {
		this.producto = producto;
	}

	@Override
	public void run() {
		// Consulto el precio.
		@SuppressWarnings("unused")
		double precio = producto.getPrecio();
	}

}
