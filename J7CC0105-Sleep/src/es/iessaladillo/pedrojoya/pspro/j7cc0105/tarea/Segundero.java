package es.iessaladillo.pedrojoya.pspro.j7cc0105.tarea;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

// Imprime la hora actual durante 10 segundos.
public class Segundero implements Runnable {

	@Override
	public void run() {
		Date fecha;	// Fecha actual.
		SimpleDateFormat formateador = 
				new SimpleDateFormat("HH:mm:ss"); // Formateador de horas.
		for (int i = 0; i < 10; i++) {
			// Obtengo la fecha y hora actual.
			fecha = new Date();
			// Muestro la hora por pantalla.
			System.out.printf("%s\n", "Hora actual -> " + formateador.format(fecha));
			// Duermo durante un segundo.
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// Si el hilo es interrumpido cuando estaba durmiendo
				// se produce esta excepción. Informo y termino el hilo.
				System.out.printf("El hilo de segundo ha sido marcado como interrumpido");
				// Probar qué pasa si no retornamos.
				return;
			}
		}
	}
}
