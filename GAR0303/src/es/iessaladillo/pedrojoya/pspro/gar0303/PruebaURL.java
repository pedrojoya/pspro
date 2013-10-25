package es.iessaladillo.pedrojoya.pspro.gar0303;

import java.net.MalformedURLException;
import java.net.URL;

public class PruebaURL {

    public static void main(String[] args) {
        URL url;
        try {
            url = new URL("https://www.google.es/search?q=java");
            System.out.println("URL: " + url.toString());
            System.out.println("Protocolo: " + url.getProtocol());
            System.out.println("Máquina: " + url.getHost());
            System.out.println("Puerto: " + url.getPort());
            System.out.println("Fichero: " + url.getFile());
            System.out.println("Usuario: " + url.getUserInfo());
            System.out.println("Path(): " + url.getPath());
            System.out.println("Autoridad: " + url.getAuthority());
            System.out.println("Consulta: " + url.getQuery());
            System.out.println();
        } catch (MalformedURLException e) {
            System.out.println(e);
        }

        try {
            url = new URL(
                    "http://www.iessaladillo.es:80/web/index.php?option=com_content&view=article&id=4:historia-del-centro&catid=2:datos-de-interes&Itemid=5");
            System.out.println("URL: " + url.toString());
            System.out.println("Protocolo: " + url.getProtocol());
            System.out.println("Máquina: " + url.getHost());
            System.out.println("Puerto: " + url.getPort());
            System.out.println("Fichero: " + url.getFile());
            System.out.println("Usuario: " + url.getUserInfo());
            System.out.println("Path(): " + url.getPath());
            System.out.println("Autoridad: " + url.getAuthority());
            System.out.println("Consulta: " + url.getQuery());
        } catch (MalformedURLException e) {
            System.out.println(e);
        }
    }

}
