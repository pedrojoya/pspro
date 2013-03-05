/**
 * 
 */
package ejercicio2;

/**
 * @author Annais
 *
 */
public class Juan implements Runnable{

	Platos lista1;
	Platos lista2;
	
	Juan(Platos lista1,Platos lista2){
		this.lista1=lista1;
		this.lista2=lista2;
	}
	
	public void run() {
		int num = 0;
		for(int i=0;i<25;i++){
			try {
				num=lista1.cojer();
				Thread.sleep((int) Math.floor(Math.random()*5000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			lista2.colocar(num);
			System.out.println("Plato "+num+" Colocado en Seco.");
		}
		
	}
}
