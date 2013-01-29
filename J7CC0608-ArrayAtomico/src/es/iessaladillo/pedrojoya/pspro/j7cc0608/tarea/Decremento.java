package es.iessaladillo.pedrojoya.pspro.j7cc0608.tarea;

import java.util.concurrent.atomic.AtomicIntegerArray;

// Decrementa todos los elementos del array.
public class Decremento implements Runnable {

    private AtomicIntegerArray array;

    // Constructor. Recibe el array.
    public Decremento(AtomicIntegerArray array) {
        this.array = array;
    }

    @Override
    public void run() {
        // Decremento todos los elementos del array.
        for (int i = 0; i < array.length(); i++) {
            array.getAndDecrement(i);
        }
    }

}