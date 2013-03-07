package es.iessaladillo.pedrojoya.pspro.j7cc0404.main;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import es.iessaladillo.pedrojoya.pspro.j7cc0404.tarea.SistemaValidacion;
import es.iessaladillo.pedrojoya.pspro.j7cc0404.tarea.Tarea;

/**
 * Creo dos sistemas de validación del usuario y muestra un mensaje con el
 * nombre del sistema que primero lo ha conseguido. Si el usuario no es validado
 * por ninguno de los dos sistemas, se informa de que el usuario no es válido.
 */
public class Main {

	public static void main(String[] args) {
		// Datos del usuario.
		String usuario = "demo";
		String password = "demo";
		// Creo dos sistemas de validación y sus tareas: contra LDAP y contra
		// BD.
		SistemaValidacion sistemaLDAP = new SistemaValidacion("LDAP");
		SistemaValidacion sistemaBD = new SistemaValidacion("BD");
		Tarea tareaLDAP = new Tarea(sistemaLDAP, usuario, password);
		Tarea tareaBD = new Tarea(sistemaBD, usuario, password);
		// Añado las dos tareas a las lista de tareas.
		List<Tarea> listaTareas = new ArrayList<>();
		listaTareas.add(tareaLDAP);
		listaTareas.add(tareaBD);
		// Creo el ejecutor ChachedThreadPool.
		ExecutorService ejecutor = (ExecutorService) Executors
				.newCachedThreadPool();
		// Envío la lista de tareas al ejecutor y espero el resultado de la
		// primera
		// tarea en terminar sin lanzar una excepción, obteniendo su resultado.
		String quienHaValidado;
		try {
			quienHaValidado = ejecutor.invokeAny(listaTareas);
			System.out.printf("Usuario validado por %s\n", quienHaValidado);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			// Si ninguno de los sistema ha finalizado bien y no ha lanzado una
			// excepción, significa que ningún sistema ha validado al usuario.
			System.out.printf("USUARIO INCORRECTO");
		}
		// Finalizo el ejecutor.
		ejecutor.shutdown();
	}

}