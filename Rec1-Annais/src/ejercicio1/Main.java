/**
 * 
 */
package ejercicio1;

/**
 * @author Annais
 *
 */
public class Main {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		
		Lista lista=new Lista();
		Persona perso1=new Persona(lista);
		Persona perso2=new Persona(lista);
		Persona perso3=new Persona(lista);
		Thread hilo1=new Thread(perso1);
		Thread hilo2=new Thread(perso2);
		Thread hilo3=new Thread(perso3);
				
		hilo1.start();
		hilo2.start();
		hilo3.start();
		hilo1.join();
		hilo2.join();
		hilo3.join();
		
		lista.listarLista();
		lista.resultado();

	}

}
