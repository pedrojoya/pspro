package es.iessaladillo.pedrojoya.pspro.j7cc0106.main;

import java.text.SimpleDateFormat;
import java.util.Date;

import es.iessaladillo.pedrojoya.pspro.j7cc0106.tarea.Cargador;
import es.iessaladillo.pedrojoya.pspro.j7cc0106.tarea.Conector;

public class Main {

	public static void main(String[] args) {

		// Inicio un hilo que cargue los datos.
		Cargador cargador = new Cargador();
		Thread hiloCargador = new Thread(cargador,"Hilo del Cargador");
		hiloCargador.start();

		// Inicio un hilo que establezca la conexión.
		Conector conector = new Conector();
		Thread hiloConexion = new Thread(conector,"Hilo de Conexión");
		hiloConexion.start();

		// Para poder continuar debo esperar que a finalicen tanto el hilo del
		// cargador de datos como el hilo de establecimiento de la conexión.
		try {
			hiloCargador.join();
			hiloConexion.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Informo al usuario.
		
		System.out.printf("%s -> Carga y conexión realizadas, continuando con el programa...\n",
				new SimpleDateFormat("HH:mm:ss").format(new Date()));
	}
}
