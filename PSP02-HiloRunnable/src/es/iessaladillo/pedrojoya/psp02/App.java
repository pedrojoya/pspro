package es.iessaladillo.pedrojoya.psp02;

// Clase que implementa la interfaz Runnable.
class Hilo implements Runnable {

	int num;

	public Hilo(int num) {
		this.num = num;
	}

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
		// Creamos un objeto Thread y le pasamos al constructor un
		// objeto de una clase que implemente la interfaz Runnable.
		Thread hilo1 = new Thread(new Hilo(1));
		Thread hilo2 = new Thread(new Hilo(2));
		hilo1.start();
		hilo2.start();
	}

}
