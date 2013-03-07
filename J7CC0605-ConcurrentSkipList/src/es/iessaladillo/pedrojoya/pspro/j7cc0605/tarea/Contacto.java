package es.iessaladillo.pedrojoya.pspro.j7cc0605.tarea;

// Simula un contacto, con su nombre y n�mero de tel�fono.
public class Contacto {

	// Propiedades.
	private String nombre;
	private String tlf;

	// Constructor. Recibe nombre y tel�fono.
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
