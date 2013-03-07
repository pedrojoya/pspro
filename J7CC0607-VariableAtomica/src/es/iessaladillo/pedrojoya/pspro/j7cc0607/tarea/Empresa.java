package es.iessaladillo.pedrojoya.pspro.j7cc0607.tarea;

import java.util.Random;
import java.util.concurrent.TimeUnit;

// Simula una empresa que realiza abonos en la cuenta.
public class Empresa implements Runnable {

	private Cuenta cuenta;
	private Random aleatorio = new Random();

	// Constructor. Recibe la cuenta.
	public Empresa(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	@Override
	public void run() {
		// Realizo 10 abonos de 1000 euros.
		for (int i = 0; i < 10; i++) {
			cuenta.abonar(1000);
			System.out.printf("Abono. Nuevo Saldo: %d\n", cuenta.getSaldo());
			// Duermo aleatoriamente.
			try {
				TimeUnit.SECONDS.sleep(aleatorio.nextInt(2));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
