public class Main {

    public static void main(String[] args) {
        Producto producto = new Producto(100);
        // Clientes
        Thread hilosClientes[]= new Thread[4];
        for (int i = 0; i < 4; i++) {
            hilosClientes[i] = new Thread(new Cliente(producto), "Cliente " + i);
        }
        // Tienda
        Thread hiloTienda = new Thread(new Tienda(producto), "Tienda");
        hiloTienda.start();
        // Se espera 1 segundo para iniciar los clientes.
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Se inician los dos primeros hilos clientes.
        for (int i = 0; i < 2; i++){
            hilosClientes[i].start();
        }
        // Se espera 7 segundos para crear el resto de los clientes.
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Se inician los dos hilos clientes restantes.
        for (int i = 2; i < 4; i++){
            hilosClientes[i].start();
        }
        // Comprobar que la lectura bloquea la escritura pero no la lectura
        // y que la escritura bloquea tanto la lectura como la escritura.
    }

}