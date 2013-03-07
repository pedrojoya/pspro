package es.iessaladillo.pedrojoya.pspro.j7cc0405.tarea;

// Encapsula el resultado de una tarea.
public class Resultado {

	private String nombre;
	private int valor;

	Resultado(String nombre, int valor) {
		this.nombre = nombre;
		this.valor = valor;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

}
