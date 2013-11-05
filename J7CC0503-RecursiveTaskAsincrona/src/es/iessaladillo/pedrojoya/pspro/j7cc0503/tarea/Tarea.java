package es.iessaladillo.pedrojoya.pspro.j7cc0503.tarea;

import java.util.List;
import java.util.concurrent.RecursiveTask;

// Modela una tarea que suma el precio de los productos de una lista.
// Si tiene que sumar 10 o más productos, la tarea original suma los 10 
// primeros y crea una nueva subtarea para el resto, que se envía al 
// ejecutor para que se ejecute de manera asíncrona. 
// Cuando la tarea original haya terminado de sumar el precio de los 10
// productos, debe esperar el resultado de la subtarea y obtener la 
// suma de los resultados parciales.
public class Tarea extends RecursiveTask<Integer> {

    // SerialVersionUID (identificador). Requerido por la intefaz serializable
    // implementada por la clase ForkJoinTask y sus subclases.
    private static final long serialVersionUID = 1L;

    // Variables miembro.
    private List<Producto> productos; // Lista de productos.
    private int desde; // Índice de la lista desde el que debe trabajar.
    private int hasta; // Índice final de la lista de trabajo (no incluido).

    // Constructor. Recibe la lista y el intervalo de trabajo (desde, hasta).
    public Tarea(List<Producto> productos, int desde, int hasta) {
        this.productos = productos;
        this.desde = desde;
        this.hasta = hasta;
    }

    // Método principal de RecursiveTask.
    @Override
    protected Integer compute() {
        int resultado = 0;
        // Se informa del cometido de la tarea.
        System.out.println("Sumar productos [" + desde + ", " + hasta + ")");
        // Si se deben sumar hasta 10 productos, se hace directamente.
        if (hasta - desde <= 10) {
            resultado = sumaPrecios(desde, hasta);
        } else {
            // Se crea la subtarea (con todos los elementos menos los 10
            // primeros).
            Tarea subtarea = new Tarea(productos, desde + 10, hasta);
            // Se inicia la ejecución asíncrona de la subtarea.
            subtarea.fork();
            // Se realiza la suma de los diez primeros.
            System.out.println("Sumar productos [" + desde + ", "
                    + (desde + 10) + ")");
            resultado = sumaPrecios(desde, desde + 10);
            // Se espera la finalización de la ejecución de la subtarea y se
            // suma su resultado al resultado parcial.
            resultado += subtarea.join();
        }
        // Se retorna el resultado.
        return new Integer(resultado);
    }

    // Suma los precios de la lista (en un intervalo).
    private int sumaPrecios(int inferior, int superior) {
        // Se recorre la lista sumando el precio de los productos.
        int suma = 0;
        for (int i = inferior; i < superior; i++) {
            Producto producto = productos.get(i);
            suma += producto.getPrecio();
        }
        // Se retorna la suma.
        return suma;
    }

}
