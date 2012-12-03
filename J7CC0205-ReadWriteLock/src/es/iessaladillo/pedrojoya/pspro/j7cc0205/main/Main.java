package es.iessaladillo.pedrojoya.pspro.j7cc0205.main;

import es.iessaladillo.pedrojoya.pspro.j7cc0205.tarea.Producto;
import es.iessaladillo.pedrojoya.pspro.j7cc0205.tarea.Cliente;
import es.iessaladillo.pedrojoya.pspro.j7cc0205.tarea.Tienda;

public class Main {

    public static void main(String[] args) {
        // Creo el producto con un precio inicial de 100.
        Producto producto = new Producto(100);
        // Voy a crear 4 hilos clientes que consultarán el mismo producto.
        Thread hilosClientes[]= new Thread[4];
        // Creo e inicio 1 hilo para la tienda
        Thread hiloTienda = new Thread(new Tienda(producto), "Tienda");
        hiloTienda.start();
        // Espero 1 segundo para crear los clientes.
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // Creo e inicio los dos primeros hilos clientes.
        for (int i = 0; i < 2; i++){
            hilosClientes[i] = new Thread(new Cliente(producto), "Cliente " + i);
            hilosClientes[i].start();
        }
        // Espero 7 segundos para crear el resto de los clientes.
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // Creo e inicio los dos hilos clientes restantes.
        for (int i = 2; i < 4; i++){
            hilosClientes[i] = new Thread(new Cliente(producto), "Cliente " + i);
            hilosClientes[i].start();
        }
        // Comprobar que la lectura bloque la escritura pero no la lectura
        // y que la escritura bloquea tanto la lectura como la escritura.
    }

}
