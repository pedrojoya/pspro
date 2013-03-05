package ejercicio2;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class Almacen {
	// Constantes.
	//private static final int LIMITE = 25;
	Random aleatorio = new Random();
	
	// Variables miembro.
	private ArrayList<Integer> platosFregados = new ArrayList<Integer>();
	private ArrayList<Integer> platosSecados = new ArrayList<Integer>();
	private ArrayList<Integer> platosColocados = new ArrayList<Integer>();

	private Object cerrojo1 = new Object();
	private Object cerrojo2 = new Object();
	
	SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss");
	    
	public void colocar(String nombre, int plato) throws InterruptedException {
		if(nombre == "Pedro"){
			Thread.sleep(aleatorio.nextInt(5000));
			synchronized (cerrojo1) {
				platosFregados.add(plato);
				// Informar hora
				System.out.println(hora.format(new Date()) + " > plato fregado " + plato);
					
				// Notifico por si Juan está esperando para secar el plato
				cerrojo1.notifyAll();	
			}
		}
		else {	
			if(nombre == "Juan"){
				Thread.sleep(aleatorio.nextInt(5000));
				synchronized (cerrojo1) {
					Thread.sleep(aleatorio.nextInt(5000));
					while (platosFregados.isEmpty()) {
						cerrojo1.wait();
		            }
					// REUTILIZAS EL PARÁMETRO plato. QUEDA FEO.
					plato = platosFregados.remove(0);
				}
				
				synchronized (cerrojo2) {
					// Llevo el plato a la pila de platos secos
					platosSecados.add(plato);
					// Informar hora
					System.out.println(hora.format(new Date()) + " > plato secado " + plato);
				
					// Notifico por si Pepe está esperando para colocar el plato en la alacena
		            cerrojo2.notifyAll();	
				}
			}
			else {
				if(nombre == "Pepe"){
					Thread.sleep(aleatorio.nextInt(5000));
					synchronized (cerrojo2) {
						while (platosSecados.isEmpty()) {
							cerrojo2.wait();
			            }
						// Llevo el plato a la alacena
						plato = platosSecados.remove(0);
						platosColocados.add(plato);
					}
					
					// Informar hora
					System.out.println(hora.format(new Date()) + " > plato colocado " + plato);
					
					// Notificar aquí ya no hace falta
		           // cerrojo2.notifyAll();	
				}
			}
			
		}
	}
}
		