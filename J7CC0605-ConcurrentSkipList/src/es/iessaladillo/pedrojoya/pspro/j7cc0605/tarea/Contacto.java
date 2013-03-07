package es.iessaladillo.pedrojoya.pspro.j7cc0605.tarea;

// Simula un contacto, con su nombre y número de teléfono.
public class Contacto {

	// Propiedades.
	private String nombre;
	private String tlf;

	// Constructor. Recibe nombre y teléfono.
	public Contacto(String nombre, String tlf) {
		this.nombre = nombre;
		this.tlf = tlf;
	}

	// Getters.
	public String getNombre() {
		return nombre;
	}

	public String getTlf() {
		return tlf;
	}

}
