// Simula la tienda que vende el producto.
public class Tienda implements Runnable {

    private Producto producto;

    public Tienda(Producto producto){
        this.producto = producto;
    }

    @Override
    public void run() {
        // Se incrementa en 20 euros el precio 3 veces.
        double precio = 100.0;
        for (int i = 0; i < 3; i++) {
            precio += 20;
            producto.setPrecio(precio);
        }
    }

}
