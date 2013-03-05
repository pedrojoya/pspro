package ejercicio2;

public class Pepe extends Thread {
    // Variables 
    private Almacen almacen;
    int platoColocado = 1;
    String nombre = "Pepe";

    // Constructor.
    public Pepe(Almacen almacen) {
        // Llamo al constructor del padre.
        super();
        // Almaceno una copia de los parámetros del constructor.
        this.almacen = almacen;
    }

    // Método que ejecuta el hilo
    @Override
    public void run() {
    	for(int i=0; i<25; i++){
	        try {
				almacen.colocar(nombre, platoColocado++);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
}