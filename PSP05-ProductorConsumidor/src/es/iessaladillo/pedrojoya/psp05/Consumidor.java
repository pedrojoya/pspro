package es.iessaladillo.pedrojoya.psp05;

public class Consumidor extends Thread {

	// Variables miembro.
	private Almacen almacen;

	public Consumidor(Almacen almacen) {
		// Llamo al constructor del padre.
		super();
		// Almaceno una copia de los parámetros del constructor.
		this.almacen = almacen;
	}

	@Override
	public void run() {
		Object elemento;
		// Obtengo un elemento del almacén y lo consumo.
		for (;;) {
			elemento = almacen.sacar();
			consumir(elemento);
			try {
				this.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void consumir(Object elemento) {
		System.out.println("Consumido: " + elemento);
	}
	
}
