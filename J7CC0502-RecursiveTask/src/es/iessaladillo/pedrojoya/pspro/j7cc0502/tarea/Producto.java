package es.iessaladillo.pedrojoya.pspro.j7cc0502.tarea;

// Modela un producto, con su nombre y precio.
public class Producto {

	// Propiedades.
	private String nombre;
	private double precio;

	// Constructor. Recibe el nombre y el precio.
	public Producto(String nombre, double precio) {
		this.nombre = nombre;
		this.precio = precio;
	}

	// Getters y Setters.
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

}
