package ejercicio1;

public class Pizarra {
	 // Constantes.
    //private int tirada;
    
    // Variables miembro.
    private int[] almacen = {0, 0, 0, 0, 0, 0};
    
    // MAS ELEGANTE ASÍ:
    // private int[] almacen = new int[6];
    // ADEMÁS SE AUTOINICIALIZA.

    //private ArrayList<Integer> lista = new ArrayList<Integer>();
    //private Object cerrojo = new Object();
    private Object[] cerrojo = new Object[6];
    
    // Constructor
    public Pizarra() {
    	for(int i=0; i < 6; i++){
    		cerrojo[i] = new Object();
    	}
    }
    
    
    public void anotar(int tirada) throws InterruptedException {
        synchronized (cerrojo[tirada]) {                 
            // Agrego el elemento al almacén.
            almacen[tirada] = almacen[tirada] + 1;   
        }
    }
    
    public void mostrarResultados() {
    	System.out.println("==================");   
    	System.out.println("Listado");   
    	System.out.println("==================");   
    	for(int i=0; i<6; i++) {
    		System.out.println((i + 1) + "..... " + almacen[i] + " veces");   
        }
    }
}
