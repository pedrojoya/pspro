package es.iessaladillo.pedrojoya.pspro.j7cc0203;

import java.util.ArrayList;

public class Almacen {

    // Constantes.
    private static final int LIMITE = 10;
    
    // Variables miembro.
    private ArrayList<Integer> lista = new ArrayList<Integer>();
    private Object cerrojo = new Object();
    
    public void meter(Integer elemento) throws InterruptedException {
        synchronized (cerrojo) {
            // Espero mientras esté lleno.
            while (lista.size() >= LIMITE) {
                cerrojo.wait();
            }
            // Agrego el elemento al almacén.
            lista.add(elemento);
            // Informo.
            System.out.println("Almacenado: " + elemento);
            // Notifico por si hay algún consumidor esperando
            // que haya algún elemento.
            cerrojo.notifyAll();
        }
    }

    public Integer sacar() throws InterruptedException {
        Integer elemento;
        synchronized (cerrojo) {
            // Espero hasta que haya algo en la lista.
            while (lista.isEmpty()) {
                cerrojo.wait();
            }
            // Obtengo el primer elemento de la lista (cola FIFO).
            elemento = lista.remove(0);
            // Informo.
            System.out.println("Extraido: " + elemento);
            // Notifico por si hay algún productor esperando que
            // no esté el almacén lleno.
            cerrojo.notifyAll();
            // Retorno el elemento extraído.
            return elemento;
        }
    }

}
