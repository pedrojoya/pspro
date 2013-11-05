package es.iessaladillo.pedrojoya.pspro.j7cc0502.tarea;

import java.util.List;
import java.util.concurrent.RecursiveTask;

// Modela una tarea que suma el precio de los productos de una lista.
// Si tiene que sumar 10 o más productos, divide el trabajo en dos
// subtareas y las ejecuta sumando los resultados parciales obtenidos.
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
        // Si se deben sumar como máximo 10 productos, se hace directamente.
        if (hasta - desde <= 10) {
            resultado = sumaPrecios();
        } else {
            // Se obtiene el elemento central de la lista.
            int mitad = (hasta + desde) / 2;
            System.out.printf("[%d,%d) descompuesta en [%d,%d) y [%d,%d)\n",
                    desde, hasta, desde, mitad + 1, mitad + 1, hasta);
            // Se crean dos subtareas asignándoles la mitad de los índices.
            Tarea subtarea1 = new Tarea(productos, desde, mitad + 1);
            Tarea subtarea2 = new Tarea(productos, mitad + 1, hasta);
            // Se ejecutan las dos subtareas, quedando en espera de que terminen
            // (llamada síncrona).
            invokeAll(subtarea1, subtarea2);
            // Se obtiene el resultado de la tarea a partir de los resultados de
            // las subtareas.
            try {
                resultado = subtarea1.get().intValue()
                        + subtarea2.get().intValue();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // Se retorna el resultado.
        return new Integer(resultado);
    }

    // Suma los precios de la lista (en un intervalo).
    private int sumaPrecios() {
        // Se recorre la lista sumando el precio de los productos.
        int suma = 0;
        for (int i = desde; i < hasta; i++) {
            Producto producto = productos.get(i);
            suma += producto.getPrecio();
        }
        // Se retorna la suma.
        return suma;
    }

}
