package es.iessaladillo.pedrojoya.psp05;

import java.util.ArrayList;

public class Almacen {

    // Constantes.
    private static final int LIMITE = 10;
    
    // Variables miembro.
    private ArrayList<Integer> lista = new ArrayList<Integer>();
    
    public void meter(Integer elemento) throws InterruptedException {
        synchronized (this) {
            // Espero mientras est� lleno.
            while (lista.size() >= LIMITE) {
                this.wait();
            }
            // Agrego el elemento al almac�n.
            lista.add(elemento);
            // Informo.
            System.out.println("Almacenado: " + elemento);
            // Notifico por si hay alg�n consumidor esperando
            // que haya alg�n elemento.
            this.notifyAll();
        }
    }

    public Integer sacar() throws InterruptedException {
        Integer elemento;
        synchronized (this) {
            // Espero hasta que haya algo en la lista.
            while (lista.isEmpty()) {
                this.wait();
            }
            // Obtengo el primer elemento de la lista (cola FIFO).
            elemento = lista.remove(0);
            // Informo.
            System.out.println("Extraido: " + elemento);
            // Notifico por si hay alg�n productor esperando que
            // no est� el almac�n lleno.
            this.notifyAll();
            // Retorno el elemento extra�do.
            return elemento;
        }
    }

}
