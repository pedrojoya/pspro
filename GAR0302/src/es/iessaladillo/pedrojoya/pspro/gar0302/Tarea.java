package es.iessaladillo.pedrojoya.pspro.gar0302;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Tarea implements Runnable {

    // Propiedades.
    String ip;

    // Constructor.
    public Tarea(String ip) {
        this.ip = ip;
    }

    @Override
    public void run() {
        try {
            InetAddress direccion = InetAddress.getByName(ip);
            System.out.println(direccion.getHostName() + "/"
                    + direccion.getHostAddress());
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
