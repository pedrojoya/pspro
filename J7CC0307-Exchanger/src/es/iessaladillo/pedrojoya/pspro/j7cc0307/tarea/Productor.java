package es.iessaladillo.pedrojoya.pspro.j7cc0307.tarea;

import java.util.List;
import java.util.concurrent.Exchanger;

public class Productor implements Runnable {

    // Buffer en el que se guardan los datos.
    private List<String> buffer;
    
    // Intercambiador para sincronizar con el consumidor
    // e intercambiar el buffer.
    private final Exchanger<List<String>> intercambiador;
    
    // Constructor. Recibe el el buffer que va a usar y el intercambiador
    // (común a productor y consumidor).
    public Productor (List<String> buffer, Exchanger<List<String>> intercambiador) {
        this.buffer = buffer;
        this.intercambiador = intercambiador;
    }
    
    // El productor tendrá 10 ciclos de producción y en cada uno de ellos
    // producirá 10 datos.
    @Override
    public void run() {
        // Por cada ciclo.
        for (int i = 0; i < 10; i++){
            // Informo del inicio del ciclo de producción.
            System.out.printf("Productor -> Ciclo %d\n", i + 1);
            // En cada ciclo se producen 10 datos.
            for (int j = 0; j < 10; j++){
                String dato = "Dato "+ ((i * 10) + j);
                System.out.printf("Productor -> Producido %s\n", dato);
                buffer.add(dato);
            }
            // Una vez concluido un ciclo de producción intercambio
            // el buffer de datos.
            try {
                // El productor envía a través de intercambiador su buffer
                // con 10 datos y recibe el buffer vacío del consumidor.
                System.out.printf("Productor -> Intercambio\n");
                buffer = intercambiador.exchange(buffer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Informo de como queda el buffer una vez intercambiado.
            System.out.printf("Productor -> Tamaño del buffer: %d\n", buffer.size());
        }
    }

}