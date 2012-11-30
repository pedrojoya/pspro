package es.iessaladillo.pedrojoya.pspro.j7cc0205.tarea;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

// Simula un producto con su precio, que podrá ser consultado o modificado.
public class Producto {

	// Propiedades.
	private double precio;
	// Cerrojo de Lectura/Escritura.
	private ReadWriteLock cerrojoLE = new ReentrantReadWriteLock();
	SimpleDateFormat formateador = new SimpleDateFormat("HH:mm:ss");
	
	// Constructor.
	public Producto(double precio){
		this.precio = precio;
	}

	// Retorna el precio del producto.
	public double getPrecio() {
		// Cierro el cerrojo de lectura.
		cerrojoLE.readLock().lock();
		// Informo.
		System.out.printf("%s -> %s - Consultando precio...\n", 
				formateador.format(new Date()), 
				Thread.currentThread().getName());
		// Obtengo el precio simulando que se tarda 3 segundos.
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		double valor = precio;
		// Informo.
		System.out.printf("%s -> %s - Precio: %.2f\n", 
				formateador.format(new Date()), 
				Thread.currentThread().getName(),
				valor);
		// Libero el cerrojo de lectura.
		cerrojoLE.readLock().unlock();
		// Retorno el precio.
		return valor;
	}

	// Establece el precio.
	public void setPrecio(double precio) {
		cerrojoLE.writeLock().lock();
		// Informo.
		System.out.printf("%s -> %s - Cambiando precio...\n", 
				formateador.format(new Date()), 
				Thread.currentThread().getName());
		// Cambio el precio simulando que se tarda 4 segundos.
		try {
			TimeUnit.SECONDS.sleep(4);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.precio = precio;
		// Informo.
		System.out.printf("%s -> %s - Nuevo Precio: %.2f\n", 
				formateador.format(new Date()), 
				Thread.currentThread().getName(),
				this.precio);
		cerrojoLE.writeLock().unlock();
	}
}