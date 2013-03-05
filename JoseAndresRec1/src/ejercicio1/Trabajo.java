package ejercicio1;

import java.sql.Array;
import java.util.Random;


public class Trabajo implements Runnable{
	Random generador = new Random();
	Resultado resultados;
	int numero;
	public Trabajo(Resultado resultados) {
		
		this.resultados = resultados;
	}

	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i = 0; i<10000; i++){
			numero=generador.nextInt(6);
			resultados.tirada(numero);
		}
	}

}
