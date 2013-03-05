package ejercicio2;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class Alacena {
	int dormir;
	Random aleatorio = new Random();
	ArrayList limpios = new ArrayList();
	ArrayList secos = new ArrayList();
	ArrayList alacena = new ArrayList();
	Object pilaLimp = new Object();
	Object pilaSec = new Object();
	// Hora
	SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss");
	public void fregar(int elemento) throws InterruptedException{
		synchronized (pilaLimp){
			limpios.add(elemento);
			System.out.println(hora.format(new Date())+": Pedro ha colocado el plato "+elemento+" en la pila de limpios");
			pilaLimp.notifyAll();
		}
		dormir=aleatorio.nextInt(5000);
		Thread.sleep(dormir);
	}
	
	public void secar(int elemento) throws InterruptedException{
		synchronized (pilaLimp){
			while(limpios.isEmpty())
				pilaLimp.wait();
			limpios.remove(0);
			pilaLimp.notifyAll();
		}
		dormir=aleatorio.nextInt(5000);
		Thread.sleep(dormir);
		synchronized (pilaSec){
			secos.add(elemento);
			System.out.println(hora.format(new Date())+": Juan ha colocado el plato "+elemento+" en la pila de secos");
			pilaSec.notifyAll();
		}	
	}

	public void colocar(int elemento) throws InterruptedException{
		synchronized (pilaSec){
			while(secos.isEmpty())
				pilaSec.wait();
			secos.remove(0);
		}
		alacena.add(elemento);
		dormir=aleatorio.nextInt(5000);
		Thread.sleep(dormir);
		System.out.println(hora.format(new Date())+": Pepe ha colocado el plato "+elemento+" en la alacena");
	}
}
