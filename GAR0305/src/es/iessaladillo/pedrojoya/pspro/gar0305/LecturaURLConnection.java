package es.iessaladillo.pedrojoya.pspro.gar0305;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class LecturaURLConnection {

    public static void main(String[] args) {
        try {
            // Se obtiene el objeto URL.
            URL url = new URL("http://getbootstrap.com");
            // Se abre la conexi�n.
            URLConnection conexion = url.openConnection();
            // Se lee l�nea a l�nea y se muestra por la salida est�ndar.
            BufferedReader lector = new BufferedReader(new InputStreamReader(
                    conexion.getInputStream()));
            String linea;
            while ((linea = lector.readLine()) != null) {
                System.out.println(linea);
            }
            // Se cierra el lector.
            lector.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
