/**
 * 
 */
package ejercicio2;

/**
 * @author Annais
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Platos lisPlatoLaba=new Platos();
		Platos listPlatoSeco=new Platos();
		
		Pedro pedro=new Pedro(lisPlatoLaba);
		Juan juan=new Juan(lisPlatoLaba,listPlatoSeco);
		Pepe pepe=new Pepe(listPlatoSeco);
		
		Thread hilo1=new Thread(pedro);
		Thread hilo2=new Thread(juan);
		Thread hilo3=new Thread(pepe);
		
		hilo1.start();
		hilo2.start();
		hilo3.start();
		

	}

}
