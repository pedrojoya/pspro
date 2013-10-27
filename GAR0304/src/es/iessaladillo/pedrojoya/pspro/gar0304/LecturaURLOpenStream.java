package es.iessaladillo.pedrojoya.pspro.gar0304;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class LecturaURLOpenStream {

    public static void main(String[] args) {

        // Se obtiene la URL.
        URL url = null;
        try {
            url = new URL("http://getbootstrap.com");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        // Se obtiene el flujo de entrada desde la URL mediante su método
        // openStream().
        BufferedReader lector;
        try {
            InputStream entrada = url.openStream();
            lector = new BufferedReader(new InputStreamReader(entrada));
            // Se lee línea a línea y se muestra por pantalla
            String linea;
            while ((linea = lector.readLine()) != null) {
                System.out.println(linea);
            }
            // Se cierra el lector.
            lector.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
