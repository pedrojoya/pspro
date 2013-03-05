package ejercicio2;

public class Pedro extends Thread {
    // Variables 
    private Almacen almacen;
    int platoFregado = 1;
    String nombre = "Pedro";

    // Constructor
    public Pedro(Almacen almacen) {
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
				almacen.colocar(nombre, platoFregado++);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
}
