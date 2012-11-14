package es.iessaladillo.pedrojoya.psp05;

public class Productor extends Thread {

	// Variables miembro.
	private Almacen almacen;
	private int contador;

	// Constructor.
	public Productor(Almacen almacen) {
		// Llamo al constructor del padre.
		super();
		// Almaceno una copia de los parámetros del constructor.
		this.almacen = almacen;
		contador = 0;
	}

	// Método que ejecuta el hilo.
	@Override
	public void run() {
		Object elemento;
		// Produzco un elemento y lo guardo en el almacén.
		for (;;) {
			elemento = producir();
			almacen.meter(elemento);
			try {
				this.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// Produce un nuevo elemento
	private Object producir() {
		String elemento = "elemento " + contador;
		contador++;
		System.out.println("Producido: " + elemento);
		return elemento;
	}
}
