package es.iessaladillo.pedrojoya.pspro.j7cc0608.tarea;

import java.util.concurrent.atomic.AtomicIntegerArray;

// Incrementa todos los elementos del array.
public class Incremento implements Runnable {

    private AtomicIntegerArray array;

    // Constructor. Recibe el array.
    public Incremento(AtomicIntegerArray array) {
        this.array = array;
    }

    @Override
    public void run() {
        // Incremento todos los elementos del array.
        for (int i = 0; i < array.length(); i++) {
            array.getAndIncrement(i);
        }
    }

}