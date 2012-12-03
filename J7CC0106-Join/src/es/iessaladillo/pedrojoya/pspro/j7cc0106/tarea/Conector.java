package es.iessaladillo.pedrojoya.pspro.j7cc0106.tarea;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

// Simula el establecimiento de un conexión que tarde 8 segundos.
public class Conector implements Runnable {

	@Override
	public void run() {
		// Obtengo un formateado de hora.
		SimpleDateFormat formateador = new SimpleDateFormat("HH:mm:ss");
		// Informo del inicio de la conexión.
		System.out.printf("%s -> Conectando...\n", formateador.format(new Date()));
		// Duermo durante 8 segundos.
		try {
			TimeUnit.SECONDS.sleep(8);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// Informo del inicio de la conexión.
		System.out.printf("%s -> Conexión establecida\n", formateador.format(new Date()));
	}
}
