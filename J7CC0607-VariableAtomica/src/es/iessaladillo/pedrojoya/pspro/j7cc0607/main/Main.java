package es.iessaladillo.pedrojoya.pspro.j7cc0607.main;

import es.iessaladillo.pedrojoya.pspro.j7cc0607.tarea.Cuenta;
import es.iessaladillo.pedrojoya.pspro.j7cc0607.tarea.Banco;
import es.iessaladillo.pedrojoya.pspro.j7cc0607.tarea.Empresa;

// Crea una cuenta, una empresa y un banco para trabajar con la cuenta.
// El saldo final debería ser igual que el inicial.
public class Main {

    public static void main(String[] args) {
        // Creo una cuenta con un saldo inicial de 1000 euros.
        Cuenta cuenta = new Cuenta(1000L);
        // Creo una nueva empresa y su hilo.
        Thread hiloEmpresa = new Thread(new Empresa(cuenta));
        // Creo un nuevo banco y su hilo.
        Thread hiloBanco = new Thread(new Banco(cuenta));
        // Muestro el saldo inicial
        System.out.printf("Saldo inicial: %d\n", cuenta.getSaldo());
        // Inicio los hilos y espero su finalización.
        hiloBanco.start();
        hiloEmpresa.start();
        try {
            hiloEmpresa.join();
            hiloBanco.join();
            // Muestro el saldo final.
            System.out.printf("Saldo final: %d\n", cuenta.getSaldo());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}