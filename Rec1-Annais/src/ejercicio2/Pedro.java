/**
 * 
 */
package ejercicio2;

/**
 * @author Annais
 *
 */
public class Pedro implements Runnable{

	Platos lista2;
	
	Pedro(Platos lista2){
		this.lista2=lista2;
	}
	@Override
	public void run() {
		for(int i=0;i<25;i++){
			try {
				Thread.sleep((int) Math.floor(Math.random()*5000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			lista2.colocar(i);
			System.out.println("Plato "+i+" Colocado en Limpio.");
		}
		
	}
	
	
	
}
