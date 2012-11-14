package es.iessaladillo.pedrojoya.psp05;

public class App {

	public static void main(String[] args) {
		Almacen almacen = new Almacen();
		Productor productor = new Productor(almacen);
		Consumidor consumidor1 = new Consumidor(almacen);
		Consumidor consumidor2 = new Consumidor(almacen);
		productor.start();
		consumidor1.start();
		consumidor2.start();
	}

}
