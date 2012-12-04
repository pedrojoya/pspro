package es.iessaladillo.pedrojoya.pspro.j7cc0203;

public class Productor extends Thread {

    // Variables miembro.
    private Almacen almacen;
    int contador = 0;

    // Constructor.
    public Productor(Almacen almacen) {
        // Llamo al constructor del padre.
        super();
        // Almaceno una copia de los parámetros del constructor.
        this.almacen = almacen;
    }

    // Método que ejecuta el hilo. 
    @Override
    public void run() {
        Integer elemento;
        // Produzco un elemento y lo guardo en el almacén.
        while (true) {
            try {
                elemento = producir();
                almacen.meter(elemento);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    // Produce un nuevo elemento
    private Integer producir() {
        Integer elemento = new Integer(contador++);
        System.out.println("Producido: " + elemento);
        return elemento;
    }
}
