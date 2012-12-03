package es.iessaladillo.pedrojoya.psp03;

public class App {

	public static void main(String[] args) {
		// Defino una clase inline anónima que implementa la interfaz Runnable.
		Thread hilo1 = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i=0; i<10; i++) {
					System.out.println("Hola " + i);
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}		
			}
			
		});
		hilo1.start();
	}

}
