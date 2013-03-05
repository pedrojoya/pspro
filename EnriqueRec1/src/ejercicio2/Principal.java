package ejercicio2;

/**
 * @author Enrique
 * @version 1.0
 */
public class Principal {
	public static void main(String[] args) {
		//Creo el objeto común
		Platos plato = new Platos();
		//Creo los hilos pedro, juan y pepe
		Thread pedro = new Thread(new Fregar(plato),"Pedro");
		Thread juan = new Thread(new Secar(plato), "Juan");
		Thread pepe = new Thread(new Colocar(plato), "Pepe");
		
		pedro.start();
		juan.start();
		pepe.start();
	}
}
