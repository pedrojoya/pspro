package ejercicio1;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 */

/**
 * @author Annais
 *
 */
public class Persona implements Runnable{
	
	static Lista lista;
	Persona(Lista lista){
		this.lista=lista;
	}
	
	public int tirarDado(){
		return (int) Math.floor(Math.random()*6);	
	}

	@Override
	public void run() {
		synchronized (lista){
			for(int i=0;i<10000;i++){
				lista.contarResult(tirarDado());
			}
		}
		
	}
		
	
}
