// Cliente que consulta el precio varias veces.
public class Cliente implements Runnable {

    private Producto producto;

    public Cliente (Producto producto){
        this.producto = producto;
    }

    @Override
    public void run() {
        // Se consulta el precio.
        double precio = producto.getPrecio();
    }

}
