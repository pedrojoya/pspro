package es.iessaladillo.pedrojoya.pspro.j7cc0203;

public class Consumidor extends Thread {

    // Variables miembro.
    private Almacen almacen;

    public Consumidor(Almacen almacen) {
        // Llamo al constructor del padre.
        super();
        // Almaceno una copia de los parámetros del constructor.
        this.almacen = almacen;
    }

    @Override
    public void run() {
        Integer elemento;
        // Obtengo un elemento del almacén y lo consumo.
        while (true) {
            try {
                elemento = almacen.sacar();
                consumir(elemento);
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    private void consumir(Object elemento) {
        System.out.println("Consumido: " + elemento);
    }
    
}
