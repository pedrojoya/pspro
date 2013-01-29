package es.iessaladillo.pedrojoya.pspro.j7cc0607.tarea;

import java.util.Random;
import java.util.concurrent.TimeUnit;

// Simula un banco que saca dinero de la cuenta.
public class Banco implements Runnable {

    private Cuenta cuenta;
    private Random aleatorio = new Random();
    
    // Constructor. Recibe la cuenta.
    public Banco(Cuenta cuenta) {
        this.cuenta = cuenta;
    }
    
    @Override
    public void run() {
        // Realizo 10 cargos de 1000 euros.
        for (int i = 0; i < 10; i++){
            cuenta.cargar(1000);
            System.out.printf("Cargo. Nuevo Saldo: %d\n", cuenta.getSaldo());
            // Duermo aleatoriamente.
            try {
                TimeUnit.SECONDS.sleep(aleatorio.nextInt(2));
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
