package es.iessaladillo.pedrojoya.pspro.rama0201;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Palillo {

    // Propieades.
    private int num; // Número de palillo.
    private boolean enUso = false; // Indicador de si está en uso.

    // Auxiliares.
    SimpleDateFormat formateador = new SimpleDateFormat("HH:mm:ss");

    // Constructor.
    public Palillo(int num) {
        this.num = num;
    }

    // Un filósofo quiere coger el palillo.
    public void coger(int filosofo) {
        synchronized (this) {
            // Mientras el palillo esté en uso el filósofo deberá esperar.
            while (enUso) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            // El filósofo coge el palillo.
            enUso = true;
            // Se informa.
            System.out.println(formateador.format(new Date())
                    + " --> Filósofo " + filosofo + ": Coge el palillo " + num);
        }
    }

    // Un filósofo quiere soltar el palillo.
    public void soltar(int filosofo) {
        synchronized (this) {
            enUso = false;
            // Se informa.
            System.out.println(formateador.format(new Date())
                    + " --> Filósofo " + filosofo + ": Suelta el palillo "
                    + num);
            // Se notifica a los que estuvieran esperando el palillo, que el
            // filósofo lo ha soltado.
            this.notifyAll();
        }
    }

}
