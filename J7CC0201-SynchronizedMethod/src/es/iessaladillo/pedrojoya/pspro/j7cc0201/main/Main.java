package es.iessaladillo.pedrojoya.pspro.j7cc0201.main;

import java.text.SimpleDateFormat;
import java.util.Date;

import es.iessaladillo.pedrojoya.pspro.j7cc0201.tarea.Banco;
import es.iessaladillo.pedrojoya.pspro.j7cc0201.tarea.Cuenta;
import es.iessaladillo.pedrojoya.pspro.j7cc0201.tarea.Empresa;

public class Main {

	public static void main(String[] args) {
		// Creo una nueva cuenta con un saldo inicial de 100 euros.
		Cuenta cuenta = new Cuenta(100);
		SimpleDateFormat formateador = new SimpleDateFormat("HH:mm:ss");
		System.out.printf("%s -> Saldo inicial: %f\n",
				formateador.format(new Date()), cuenta.getSaldo());
		// Creo un nuevo hilo para una nueva empresa.
		Thread hiloEmpresa = new Thread(new Empresa(cuenta));
		hiloEmpresa.start();
		// Creo un nuevo hilo para un nuevo banco.
		Thread hiloBanco = new Thread(new Banco(cuenta));
		hiloBanco.start();
		try {
			// Espero la finalización de los hilos.
			hiloEmpresa.join();
			hiloBanco.join();
			// Muestro el saldo final.
			System.out.printf("%s -> Saldo final: %f\n",
					formateador.format(new Date()), cuenta.getSaldo());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
