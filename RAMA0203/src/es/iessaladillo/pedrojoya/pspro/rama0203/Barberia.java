package es.iessaladillo.pedrojoya.pspro.rama0203;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Barberia {

    // Propieadades.
    private final int aforo;
    private int libres;
    private ArrayList<Integer> cola = new ArrayList<Integer>();

    // Auxiliares.
    private SimpleDateFormat formateador = new SimpleDateFormat("HH:mm:ss");

    // Constructor.
    public Barberia(int aforo) {
        this.aforo = aforo;
        this.libres = aforo;
    }

    // Un cliente se pone en cola. Retorna si false si no había aforo y se ha
    // ido a otra barbería.
    public boolean ponerseEnCola(int numCliente) {
        synchronized (this) {
            // Si no hay aforo libre, el cliente no llega a entrar.
            if (libres <= 0) {
                System.out.println(formateador.format(new Date())
                        + " --> Cliente " + numCliente
                        + " no cabe en la barbería, así que se va");
                return false;
            } else {
                // Se añade a la cola.
                cola.add(numCliente);
                // Decrementa el aforo libre.
                libres--;
                // Se informa.
                System.out.println(formateador.format(new Date())
                        + " --> Cliente " + numCliente + " entra en la cola");
                // Notifica que ha entrado en la cola por si el barbero estaba
                // durmiendo.
                notifyAll();
                return true;
            }
        }
    }

    // El barbero llama al siguiente cliente en la cola.
    public int siguiente() {
        synchronized (this) {
            // Mientras no haya ningún cliente debe esperar.
            while (libres >= aforo) {
                try {
                    System.out.println(formateador.format(new Date())
                            + " --> El barbero se echa a dormir");
                    wait();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            // Extrae el primer cliente de la cola.
            int primero = cola.remove(0);
            // Se Incrementa el aforo libre.
            libres++;
            // Se informa.
            System.out.println(formateador.format(new Date())
                    + " --> El barbero llama al Cliente " + primero);
            // Se retorna al cliente extraído de la cola.
            return primero;
        }
    }

}
