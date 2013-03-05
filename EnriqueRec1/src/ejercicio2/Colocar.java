package ejercicio2;

/**
 * @author Enrique
 * @version 1.0
 */
public class Colocar implements Runnable{
	
	Platos plato;
	
	public Colocar(Platos plato){
		this.plato = plato;
	}
	@Override
	public void run() {
		try {
			for(int i = 0; i < 25; i++){
				plato.colocar();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
