package es.iessaladillo.pedrojoya.pspro.rama0201;

public class Main {

    public static void main(String[] args) {
        // Se crean los palillos.
        Palillo[] palillos = new Palillo[7];
        for (int i = 0; i < 7; i++) {
            palillos[i] = new Palillo(i);
        }
        // Se crean los hilos correspondientes a los filósofos, pasándole a cada
        // filósofo su número, su palillo izquierdo y su palillo derecho.
        Thread[] hilos = new Thread[7];
        for (int i = 0; i < 7; i++) {
            hilos[i] = new Thread(new Filosofo(i, palillos[i],
                    palillos[(i + 1) % 7]));
        }
        // Se inician todos los hilos.
        for (int i = 0; i < 7; i++) {
            hilos[i].start();
        }
    }

}
