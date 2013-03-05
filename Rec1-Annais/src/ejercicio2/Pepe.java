/**
 * 
 */
package ejercicio2;

/**
 * @author Annais
 *
 */
public class Pepe implements Runnable{
	Platos lista1;

	Pepe(Platos lista1){
		this.lista1=lista1;
	}

	@Override
	public void run() {
		for(int i=0;i<25;i++){
			try {
				lista1.cojer();
				Thread.sleep((int) Math.floor(Math.random()*5000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Plato "+i+" Colocado en Seco.");
		}
	}

}
