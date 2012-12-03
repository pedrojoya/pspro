package es.iessaladillo.pedrojoya.psp01;

// Clase que extiende la clase Thread.
class Hilo extends Thread {

	int num;

	public Hilo(int num) {
		super();
		this.num = num;
	}

	// Método que se ejecutará cuando se inicie el hilo.
	@Override
	public void run() {
		for (int i=0; i<10; i++) {
			System.out.println("Hilo " + num + ": " + i);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}

public class App {

	public static void main(String[] args) {
		// Creo un objeto de la clase creada e inicio su ejecución.
		Hilo hilo1 = new Hilo(1);
		Hilo hilo2 = new Hilo(2);
		hilo1.start();
		hilo2.start();
	}

}
