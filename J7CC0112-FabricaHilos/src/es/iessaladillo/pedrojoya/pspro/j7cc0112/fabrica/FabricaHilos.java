package es.iessaladillo.pedrojoya.pspro.j7cc0112.fabrica;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.ThreadFactory;

// Clase que crea hilos.
public class FabricaHilos implements ThreadFactory {

	// Atributos de la fábrica
	private int contador; // Contador de hilos creados.
	private String nombreFabrica; // Nombre.
	private ArrayList<String> registro; // Registro de eventos. 
	
	// Constructor.
	public FabricaHilos(String nombreFabrica) {
		// Inicializo los datos de la fábrica.
		contador = 0;
		this.nombreFabrica = nombreFabrica;
		registro = new ArrayList<String>();
	}
	
	// Crea un nuevo hilo y retorna. Recibe el runnable que debe ejecutar.
	@Override
	public Thread newThread(Runnable r) {
		// Creo el hilo con un nombre significativo que incluye el nombre de la fábrica.
		Thread hilo = new Thread(r, nombreFabrica + "-Hilo-" + contador);
		// Incremento el número de hilos creados por la fábrica.
		contador++;
		// Agrego al registro una entrada para indicar el hilo creado
		SimpleDateFormat formateador = new SimpleDateFormat("HH:mm:ss");
		registro.add(formateador.format(new Date()) + " -> " + hilo.getName() + " creado\n");
		return hilo;
	}
	
	// Retorna el registro (log) de la fábrica en formato cadena.
	public String getRegistro() {
		// Recorro el registro y construyo una cadena con todo su contenido.
		StringBuffer sRegistro = new StringBuffer();
		Iterator<String> indice = registro.iterator();
		while (indice.hasNext()) {
			// Añado la entrada del registro a la cadena.
			sRegistro.append(indice.next());
		}
		// Retorno la cadena resultante.
		return sRegistro.toString();
	}

}
