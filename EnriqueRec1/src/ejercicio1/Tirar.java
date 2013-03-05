package ejercicio1;

import java.util.Random;

/**
 * @author Enrique
 * @version 1.0
 */
public class Tirar implements Runnable{

	Pizarra pizarra;
	Random aleatorio = new Random();
	int numero;
	
	public Tirar(Pizarra pizarra){
		this.pizarra = pizarra;
	}
	
	@Override
	public void run() {
		for(int i = 0; i < 10000; i++){
			numero = aleatorio.nextInt(6);
			numero++;
			pizarra.apuntar(numero);
		}
	}
}
