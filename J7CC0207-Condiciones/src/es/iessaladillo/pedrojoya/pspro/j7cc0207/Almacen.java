package es.iessaladillo.pedrojoya.pspro.j7cc0207;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Almacen {

    // Constantes.
    private static final int LIMITE = 10;
    
    // Variables miembro.
    private ArrayList<Integer> lista = new ArrayList<Integer>();
    private ReentrantLock cerrojo = new ReentrantLock();
    private Condition hayEspacio = cerrojo.newCondition();
    private Condition hayElemento = cerrojo.newCondition();
    
    public void meter(Integer elemento) throws InterruptedException {
    	// Obtengo el cerrojo.
    	cerrojo.lock();
        try {
			// Espero mientras esté lleno.
			while (lista.size() >= LIMITE) {
			    hayEspacio.await();
			}
			// Agrego el elemento al almacén.
			lista.add(elemento);
			// Informo.
			System.out.println("Almacenado: " + elemento);
			// Notifico por si hay algún consumidor esperando
			// que haya algún elemento.
			hayElemento.signalAll();
		} catch (InterruptedException e) {
			throw e;
		} finally {
	        // Libero el cerrojo.
	        cerrojo.unlock();
		}
    }

    public Integer sacar() throws InterruptedException {
        Integer elemento = null;
        // Obtengo el cerrojo.
        cerrojo.lock();
        try {
            // Espero hasta que haya algo en la lista.
            while (lista.isEmpty()) {
                hayElemento.await();
            }
            // Obtengo el primer elemento de la lista (cola FIFO).
            elemento = lista.remove(0);
            // Informo.
            System.out.println("Extraido: " + elemento);
            // Notifico por si hay algún productor esperando que
            // no esté el almacén lleno.
            hayEspacio.signalAll();
            // Retorno el elemento extraído.
            return elemento;
        } catch (InterruptedException e) {
			throw e;
		} finally {
	        // Libero el cerrojo.
	        cerrojo.unlock();
		}
    }

}
