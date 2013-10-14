package es.iessaladillo.pedrojoya.pspro.rama0202;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Mesa {

    // Propiedades.
    private boolean[] enUso = new boolean[7]; // Uso de los palillos.

    // Auxiliar.
    SimpleDateFormat formateador = new SimpleDateFormat("HH:mm:ss");

    // Constructor.
    public Mesa() {
        for (int i = 0; i < 7; i++) {
            enUso[i] = false;
        }
    }

    // Un filósofo quiere coger sus palillos.
    public void coger(int numFilosofo) {
        // Palillos que debe coger.
        int izda = numFilosofo;
        int dcha = (numFilosofo + 1) % 7;
        synchronized (this) {
            // Mientras no estén los dos palillos disponibles, el filósofo debe
            // esperar.
            while (enUso[izda] || enUso[dcha]) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            // El filósofo coge los palillos.
            enUso[izda] = true;
            enUso[dcha] = true;
            // Se informa.
            System.out.println(formateador.format(new Date())
                    + " --> Filósofo " + numFilosofo + ": Coge los palillos "
                    + izda + " y " + dcha);
        }
    }

    // Un filósofo quiere soltar sus palillos.
    public void soltar(int numFilosofo) {
        // Palillos que debe soltar.
        int izda = numFilosofo;
        int dcha = (numFilosofo + 1) % 7;
        synchronized (this) {
            // Suelta los palillos.
            enUso[izda] = false;
            enUso[dcha] = false;
            // Se informa.
            System.out.println(formateador.format(new Date())
                    + " --> Filósofo " + numFilosofo + ": Suelta los palillos "
                    + izda + " y " + dcha);
            // Se notifica a los que estuvieran esperando palillos, que el
            // filósofo ha soltados los suyos.
            this.notifyAll();
        }
    }

}
