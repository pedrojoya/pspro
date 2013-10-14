package es.iessaladillo.pedrojoya.pspro.rama0201;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Palillo {

    // Propieades.
    private int num; // N�mero de palillo.
    private boolean enUso = false; // Indicador de si est� en uso.

    // Auxiliares.
    SimpleDateFormat formateador = new SimpleDateFormat("HH:mm:ss");

    // Constructor.
    public Palillo(int num) {
        this.num = num;
    }

    // Un fil�sofo quiere coger el palillo.
    public void coger(int filosofo) {
        synchronized (this) {
            // Mientras el palillo est� en uso el fil�sofo deber� esperar.
            while (enUso) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            // El fil�sofo coge el palillo.
            enUso = true;
            // Se informa.
            System.out.println(formateador.format(new Date())
                    + " --> Fil�sofo " + filosofo + ": Coge el palillo " + num);
        }
    }

    // Un fil�sofo quiere soltar el palillo.
    public void soltar(int filosofo) {
        synchronized (this) {
            enUso = false;
            // Se informa.
            System.out.println(formateador.format(new Date())
                    + " --> Fil�sofo " + filosofo + ": Suelta el palillo "
                    + num);
            // Se notifica a los que estuvieran esperando el palillo, que el
            // fil�sofo lo ha soltado.
            this.notifyAll();
        }
    }

}
