package es.iessaladillo.pedrojoya.pspro.j7cc0205.tarea;

// Simula la tienda que vende el producto.
public class Tienda implements Runnable {

    // Propiedades.
    private Producto producto;
    
    // Constructor.
    public Tienda(Producto producto){
        this.producto = producto;
    }
    
    @Override
    public void run() {
        // Incremento en 20 euros el precio 3 veces.
        double precio = 100.0;
        for (int i = 0; i < 3; i++) {
            precio += 20;
            producto.setPrecio(precio);
        }
    }

}
