package es.iessaladillo.pedrojoya.pspro.j7cc0303.main;

import es.iessaladillo.pedrojoya.pspro.j7cc0303.tarea.Participante;
import es.iessaladillo.pedrojoya.pspro.j7cc0303.tarea.Videoconferencia;

public class Main {

	public static void main(String[] args) {
		// Creo una videoconferencia para 10 participantes.
		Videoconferencia videoconferencia = new Videoconferencia(10);
		// Creo e inicio un hilo para la videoconferencia.
		Thread hiloVideoconferencia = new Thread(videoconferencia);
		hiloVideoconferencia.start();
		// Creo e inicio un hilo para cada uno de los diez participantes.
		for (int i = 0; i < 10; i++) {
			Participante participante = new Participante(videoconferencia,
					"Participante " + i);
			Thread hiloParticipante = new Thread(participante);
			hiloParticipante.start();
		}
	}

}