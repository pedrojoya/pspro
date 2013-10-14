package es.iessaladillo.pedrojoya.pspro.rama0203;

import java.text.SimpleDateFormat;

public class Cliente implements Runnable {

    // Propiedades.
    int num; // Número de cliente.
    Barberia barberia; // Barbería que visita el cliente.

    // Auxiliares.
    SimpleDateFormat formateador = new SimpleDateFormat("HH:mm:ss");

    // Constructor.
    public Cliente(int num, Barberia barberia) {
        this.num = num;
        this.barberia = barberia;
    }

    @Override
    public void run() {
        // El cliente pide la vez.
        barberia.ponerseEnCola(num);
    }

}
