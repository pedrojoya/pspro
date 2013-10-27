package es.iessaladillo.pedrojoya.pspro.gar0305;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class LecturaURLConnection {

    public static void main(String[] args) {
        // Se obtiene el objeto URL.
        URL url = null;
        try {
            url = new URL("http://getbootstrap.com");
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // Se abre la conexión.
        try {
            URLConnection conexion = url.openConnection();
            // Se lee línea a línea y se muestra por la salida estándar.
            BufferedReader lector = new BufferedReader(new InputStreamReader(
                    conexion.getInputStream()));
            String linea;
            while ((linea = lector.readLine()) != null) {
                System.out.println(linea);
            }
            // Se cierra el lector.
            lector.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
