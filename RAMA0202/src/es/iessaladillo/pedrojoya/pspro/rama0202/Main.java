package es.iessaladillo.pedrojoya.pspro.rama0202;

public class Main {

    public static void main(String[] args) {
        // Se crea la mesa.
        Mesa mesa = new Mesa();
        // Se crean los hilos correspondientes a los filósofos, pasándole a cada
        // filósofo su número y la mesa.
        Thread[] hilos = new Thread[7];
        for (int i = 0; i < 7; i++) {
            hilos[i] = new Thread(new Filosofo(i, mesa));
        }
        // Se inician todos los hilos.
        for (int i = 0; i < 7; i++) {
            hilos[i].start();
        }
    }

}
