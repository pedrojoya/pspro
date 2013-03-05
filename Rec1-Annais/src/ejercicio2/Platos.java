/**
 * 
 */
package ejercicio2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Annais
 *
 */
public class Platos {

	List<Integer> lista;
	
	Platos(){
		lista=new ArrayList();
	}
	
	public int cojer() throws InterruptedException{
		while(lista.isEmpty()){
			lista.wait();
		}
		int numplato=lista.remove(0);
		lista.notifyAll();
		return numplato;
	}
	
	public void colocar(int i){
		synchronized(this){
			lista.add(i);
			notifyAll();
		}
		
		
	}
	
}
