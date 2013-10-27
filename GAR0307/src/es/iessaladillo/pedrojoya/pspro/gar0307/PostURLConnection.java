package es.iessaladillo.pedrojoya.pspro.gar0307;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

public class PostURLConnection {

    public static void main(String[] args) {
        try {
            // Se obtiene el objeto URL.
            URL url = new URL("http://www.informaticasaladillo.es/mostrar.php");
            // Se abre la conexi�n.
            HttpURLConnection conexion = (HttpURLConnection) url
                    .openConnection();
            // Se env�an datos por el m�todo POST.
            conexion.setDoOutput(true);
            PrintWriter escritor = new PrintWriter(conexion.getOutputStream());
            String datos = "Nombre=Pedro&Apellido=Joya";
            escritor.print(datos);
            escritor.close();
            // Se muestra la cabecera de la respuesta.
            System.out.println("M�todo de petici�n [getRequestMethod()]: "
                    + conexion.getRequestMethod());
            System.out.println("Fecha petici�n [getDate()]: "
                    + new Date(conexion.getDate()));
            System.out.println("C�digo de respuesta [getResponseCode()]: "
                    + conexion.getResponseCode());
            System.out.println("Mensaje de respuesta [getResponseMessage()]: "
                    + conexion.getResponseMessage());
            System.out
                    .println("Fecha �ltima modificaci�n [getLastModified()]: "
                            + new Date(conexion.getLastModified()));
            System.out.println("Tipo de contenido [getContentType()]: "
                    + conexion.getContentType());
            System.out.println("Codificaci�n [getContentEncoding()]: "
                    + conexion.getContentEncoding());
            System.out.println("Tama�o del contenido [getContentLength()]: "
                    + conexion.getContentLength());
            System.out.println("Fecha expiraci�n [getExpiration()]: "
                    + new Date(conexion.getExpiration()));
            // Se obtiene la respuesta, se lee l�nea y l�nea y se escribe en la
            // salida est�ndar.
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
