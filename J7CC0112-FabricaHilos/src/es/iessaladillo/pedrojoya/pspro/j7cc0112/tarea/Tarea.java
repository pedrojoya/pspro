package es.iessaladillo.pedrojoya.pspro.j7cc0112.tarea;

import java.util.concurrent.TimeUnit;

public class Tarea implements Runnable {

	@Override
	public void run() {
		try {
			// La tarea se simula durmiendo un segundo.
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
