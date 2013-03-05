package ejercicio2;

public class Juan extends Thread {
    // Variables
    private Almacen almacen;
    int platoSecado = 1;
    String nombre = "Juan";

    // Constructor.
    public Juan(Almacen almacen) {
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
				almacen.colocar(nombre, platoSecado++);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
}