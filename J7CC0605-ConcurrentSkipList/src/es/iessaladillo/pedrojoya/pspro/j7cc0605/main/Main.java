package es.iessaladillo.pedrojoya.pspro.j7cc0605.main;

import java.util.Map;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

import es.iessaladillo.pedrojoya.pspro.j7cc0605.tarea.Contacto;
import es.iessaladillo.pedrojoya.pspro.j7cc0605.tarea.Tarea;

// Ejecuta 26 tareas en sus correspondientes hilos que almacenan contactos
// en un mapa. Posteriormente muestra parte de los contenidos del mapa.
public class Main {

	public static void main(String[] args) {
		// Creo el mapa. Las claves de los elementos son cadenas y los datos
		// son objetos Contact.
		ConcurrentSkipListMap<String, Contacto> mapa = new ConcurrentSkipListMap<>();
		// Creo e inicio 26 tareas, una para cada letra del alfabeto (inglés).
		Thread hilos[] = new Thread[26];
		int numHilo = 0;
		for (char letra = 'A'; letra <= 'Z'; letra++) {
			hilos[numHilo] = new Thread(new Tarea(mapa, String.valueOf(letra)));
			hilos[numHilo].start();
			numHilo++;
		}
		// Espero la finalización de todos los hilos.
		for (int i = 0; i < hilos.length; i++) {
			try {
				hilos[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// Informo del tamaño del mapa.
		System.out.printf("Elemento en el mapa: %d\n", mapa.size());
		// Muestro el primer elemento del mapa.
		Map.Entry<String, Contacto> elemento = mapa.firstEntry();
		Contacto contacto = elemento.getValue();
		System.out.printf("Primer elemento -> Nombre: %s; Tlf: %s\n",
				contacto.getNombre(), contacto.getTlf());
		// Muestro el último elemento del mapa.
		elemento = mapa.lastEntry();
		contacto = elemento.getValue();
		System.out.printf("Último elemento -> Nombre: %s; Tlf: %s\n",
				contacto.getNombre(), contacto.getTlf());
		// Muestro todos los elementos desde A1996 (incluido) hasta B1002 (no
		// incluido)
		System.out.printf("Submapa [A500-B500)\n");
		ConcurrentNavigableMap<String, Contacto> submapa = mapa.subMap("A500",
				"B500");
		do {
			elemento = submapa.pollFirstEntry();
			if (elemento != null) {
				contacto = elemento.getValue();
				System.out.printf("Nombre: %s; Tlf: %s\n",
						contacto.getNombre(), contacto.getTlf());
			}
		} while (elemento != null);
	}

}