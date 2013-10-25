package es.iessaladillo.pedrojoya.pspro.gar0301;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class PruebaInetAddress {

    public static void main(String[] args) {

        // Direcci�n IP.
        InetAddress dir = null;

        // Se obtiene y muestra la direcci�n de localhost.
        try {
            dir = InetAddress.getLocalHost();
            System.out.println("Localhost: " + dir.toString());
            System.out.println("M�quina de Localhost: " + dir.getHostName());
            System.out.println("IP de Localhost: " + dir.getHostAddress());
            System.out.println();
        } catch (UnknownHostException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        // Se obtiene y muestra la direcci�n de la m�quina local a partir de
        // su nombre.
        try {
            dir = InetAddress.getByName("ASUS2009");
            System.out.println("ASUS2009: " + dir.toString());
            System.out.println("M�quina de ASUS2009: " + dir.getHostName());
            System.out.println("IP de ASUS2009: " + dir.getHostAddress());
            System.out.println();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        // Se obtiene y muestra la direcci�n asociada a la m�quina
        // a la que apunta el dominio www.informaticasaladillo.es.
        try {
            dir = InetAddress.getByName("www.informaticasaladillo.es");
            System.out
                    .println("www.informaticasaladillo.es: " + dir.toString());
            System.out.println("M�quina de www.informaticasaladillo.es: "
                    + dir.getHostName());
            System.out.println("IP de www.informaticasaladillo.es: "
                    + dir.getHostAddress());
            System.out
                    .println("Nombre can�nico de www.informaticasaladillo.es: "
                            + dir.getCanonicalHostName());
            System.out.println();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        // Se obtiene y muestra la direcci�n principal asociada a la m�quina
        // a la que apunta el dominio Google Espa�a.
        try {
            dir = InetAddress.getByName("www.google.es");
            System.out.println("www.google.es: " + dir.toString());
            System.out
                    .println("M�quina de www.google.es: " + dir.getHostName());
            System.out.println("IP de www.google.es: " + dir.getHostAddress());
            System.out.println("Nombre can�nico de www.google.es: "
                    + dir.getCanonicalHostName());
            System.out.println();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        // Se obtienen y muestras todas las direcciones asociadas a la
        // m�quina a la que apunta el dominio de Google Espa�a.
        InetAddress[] direcciones;
        try {
            direcciones = InetAddress.getAllByName("www.google.es");
            System.out.println("Direcciones del dominio www.google.es: ");
            for (int i = 0; i < direcciones.length; i++) {
                System.out.println("\t" + direcciones[i].toString());
            }
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
