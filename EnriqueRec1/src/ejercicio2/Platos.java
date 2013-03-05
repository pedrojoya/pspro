package ejercicio2;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author Enrique
 * @version 1.0
 */

// LOS MENSAJES CON LOS QUE INFORMAS AL USUARIO DEL PROGRAMA NO ESTÁN MUY BIEN COLOCADOS.

public class Platos {
    
    // YA VEO QUE PARA TI UNA PILA DE PLATOS NO ES MAS QUE UN NÚMERO ENTERO ACUMULADOR.
    // COMO SE NOTA QUE TU NO FRIEGAS LOS PLATOS EN CASA ;-)
	private int pl = 0, ps = 0; 
	Object pilaLimpio = new Object();
	Object pilaSeco = new Object();
	SimpleDateFormat formateador = new SimpleDateFormat("HH:mm:ss");
	Random aleatorio = new Random();
	
	public void fregar() throws InterruptedException{
		synchronized(pilaLimpio){
			pl++;
			System.out.println("Pedro ha lavado un plato -> "+formateador.format(new Date()));
			pilaLimpio.notifyAll();
		}
		Thread.sleep(aleatorio.nextInt(2000));
	}
	
	public void secar() throws InterruptedException{
		synchronized(pilaLimpio){
			while(pl == 0){
				pilaLimpio.wait();
				// YO PONDRÍA EL MENSAJE ANTES DEL WAIT.
				System.out.println("No hay platos para secar -> "+formateador.format(new Date()));
			}
			
			pl--;
		}
		Thread.sleep(aleatorio.nextInt(1000));
		synchronized(pilaSeco){
			ps++;
			System.out.println("Juan ha secado un plato -> "+formateador.format(new Date()));
			pilaSeco.notifyAll();
		}
	}
	
	public void colocar() throws InterruptedException{
		synchronized(pilaSeco){
			while(ps == 0){
				pilaSeco.wait();
				System.out.println("No hay platos para colocar -> "+formateador.format(new Date()));
			}
			ps--;
			// ESTE MENSAJE DEBERÍA IR DESPUES DEL THREAD.SLEEP QUE TIENES AL FINAL.
			System.out.println("Pepe ha colocado un plato -> "+formateador.format(new Date()));
		}
		Thread.sleep(aleatorio.nextInt(2000));
	}
}
