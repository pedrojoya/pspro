package ejercicio1;

import java.util.Random;

public class Tirador extends Thread {
	    // Variables miembro.
	    private Pizarra pizarra;
	    int valor = 0;
	    Random aleatorio = new Random();

	    // Constructor.
	    public Tirador(Pizarra pizarra) {
	        // Llamo al constructor del padre.
	        super();
	        // Almaceno una copia de los parámetros del constructor.
	        this.pizarra = pizarra;
	    }

	    // Método que ejecuta el hilo. 
	    @Override
	    public void run() {
	        for(int i=1; i<10000; i++) {
	        	valor = aleatorio.nextInt(6);
	        	try {
					pizarra.anotar(valor);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	    }
	        
}