package es.iessaladillo.pedrojoya.pspro.j7cc0106.tarea;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

// Simula una carga de datos que dura 5 segundos. 
public class Cargador implements Runnable {

	@Override
	public void run() {
		// Obtengo un formateado de hora.
		SimpleDateFormat formateador = new SimpleDateFormat("HH:mm:ss");
		// Informo del inicio de la carga.
		System.out.printf("%s -> Cargando datos... \n", formateador.format(new Date()));
		// Duermo durante 5 segundos.
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// Informo de finalización de carga.
		System.out.printf("%s -> Carga de datos finalizada\n", formateador.format(new Date()));
	}
}
