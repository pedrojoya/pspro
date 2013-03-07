package es.iessaladillo.pedrojoya.pspro.j7cc0603.tarea;

// Simula un evento con su nombre y su prioridad.
// Debe implementar la interfaz Comparable para especificar cómo
// se ordenan los eventos, mediante el método compareTo().
public class Evento implements Comparable<Evento> {

	// Variables miembro.
	private String nombre;
	private int prioridad;

	// Constructor. Recibe nombre y prioridad.
	public Evento(String nombre, int prioridad) {
		this.nombre = nombre;
		this.prioridad = prioridad;
	}

	// Getters.
	public String getNombre() {
		return nombre;
	}

	public int getPrioridad() {
		return prioridad;
	}

	// Método que compara el evento sobre el que se ejecuta con el evento
	// recibido como parámetro, retornando un valor menor que, igual o mayor
	// que cero, dependiendo de si el primero tiene menos, igual o mayor
	// prioridad que el segundo, respectivamente.
	@Override
	public int compareTo(Evento e) {
		return this.prioridad - e.getPrioridad();
	}

}
