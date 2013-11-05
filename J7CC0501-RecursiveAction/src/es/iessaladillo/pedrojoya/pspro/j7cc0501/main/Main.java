package es.iessaladillo.pedrojoya.pspro.j7cc0501.main;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

import es.iessaladillo.pedrojoya.pspro.j7cc0501.tarea.Producto;
import es.iessaladillo.pedrojoya.pspro.j7cc0501.tarea.Tarea;

public class Main {

    public static void main(String[] args) {
        // Se crea la lista de productos.
        List<Producto> productos = new ArrayList<Producto>();
        // Se añaden 10000 productos a la lista, inicializados todo con un
        // precio de 10€.
        for (int i = 0; i < 10000; i++) {
            productos.add(new Producto("Producto " + i, 10));
        }
        // Se crea la tarea actualizadora de precios (desde el elemento 0 hasta
        // el último, incrementándo el precio un 20%).
        Tarea tarea = new Tarea(productos, 0, productos.size(), 0.20);
        // Se crea el ejecutor.
        ForkJoinPool ejecutor = new ForkJoinPool();
        // Se envía la tarea al ejecutor (llamada asíncrona).
        ejecutor.execute(tarea);
        // Se muestra información sobre el ejecutor hasta que la tarea haya
        // concluido.
        do {
            System.out.printf("Número de hilos: %d\n",
                    ejecutor.getActiveThreadCount());
            System.out.printf("Robo de hilos: %d\n", ejecutor.getStealCount());
            System.out.printf("Paralelismo: %d\n", ejecutor.getParallelism());
            // El hilo se duerme 2 milésimas.
            try {
                TimeUnit.MILLISECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (!tarea.isDone());
        // Se indica al ejecutor que debe finalizar.
        ejecutor.shutdown();
        // Se compruebo que la tarea ha finalizado normalmente.
        if (tarea.isCompletedNormally()) {
            System.out.printf("La tarea ha finalizado normalmente\n");
        }
        // Se comprueba que todos los productos se han actualizado
        // correctamente.
        for (int i = 0; i < productos.size(); i++) {
            Producto producto = productos.get(i);
            if (producto.getPrecio() != 12) {
                // Si hay algún producto mal actualizado, se informa.
                System.out.printf("Producto %s: %f\n", producto.getNombre(),
                        producto.getPrecio());
            }
        }
    }

}