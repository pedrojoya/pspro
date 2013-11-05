package es.iessaladillo.pedrojoya.pspro.j7cc0503.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

import es.iessaladillo.pedrojoya.pspro.j7cc0503.tarea.Producto;
import es.iessaladillo.pedrojoya.pspro.j7cc0503.tarea.Tarea;

// Crea una lista de productos, un ejecutor ForkJoinPool y la tarea.
public class Main {

    public static void main(String[] args) {
        Random aleatorio = new Random();
        int suma = 0;
        // Se crea la lista de productos.
        List<Producto> productos = new ArrayList<Producto>();
        // Se añaden los productos con un precio aleatorio de 1 a 10€,
        // almecenándolo en el total para poder comprobar después el resultado.
        int valor;
        for (int i = 0; i < 10000; i++) {
            valor = aleatorio.nextInt(10) + 1;
            suma += valor;
            productos.add(new Producto("Producto " + i, valor));
        }
        System.out
                .println("La suma del precio de todos los productos debe ser: "
                        + suma);
        // Se crea la tarea sumadora de los precios de todos los productos
        // (desde el elemento 0 hasta el elemento tamaño-1)
        Tarea tarea = new Tarea(productos, 0, productos.size());
        // Se crea el ejecutor.
        ForkJoinPool ejecutor = new ForkJoinPool();
        // Se envía la tarea al ejecutor.
        ejecutor.execute(tarea);
        // Se muestra información sobre el ejecutor hasta que la tarea haya
        // concluido.
        do {
            // Se duerme 2 milésimas.
            try {
                TimeUnit.MILLISECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (!tarea.isDone());
        // Se indica al ejecutor que debe finalizar.
        ejecutor.shutdown();
        // Se obtiene el resultado y se compara con el esperado.
        try {
            int resultado = tarea.get().intValue();
            if (resultado != suma) {
                System.out.println("Error en la obtención del resultado");
            } else {
                System.out
                        .println("La suma del precio de todos los productos es: "
                                + resultado);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}