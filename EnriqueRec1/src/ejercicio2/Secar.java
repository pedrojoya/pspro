package ejercicio2;

/**
 * @author Enrique
 * @version 1.0
 */
public class Secar implements Runnable{

	Platos plato;
	
	public Secar(Platos plato){
		this.plato = plato;
	}
	
	@Override
	public void run() {
		try {
			for(int i = 0; i < 25; i++){
				plato.secar();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
