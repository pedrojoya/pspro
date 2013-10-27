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
            // Se abre la conexión.
            HttpURLConnection conexion = (HttpURLConnection) url
                    .openConnection();
            // Se envían datos por el método POST.
            conexion.setDoOutput(true);
            PrintWriter escritor = new PrintWriter(conexion.getOutputStream());
            String datos = "Nombre=Pedro&Apellido=Joya";
            escritor.print(datos);
            escritor.close();
            // Se muestra la cabecera de la respuesta.
            System.out.println("Método de petición [getRequestMethod()]: "
                    + conexion.getRequestMethod());
            System.out.println("Fecha petición [getDate()]: "
                    + new Date(conexion.getDate()));
            System.out.println("Código de respuesta [getResponseCode()]: "
                    + conexion.getResponseCode());
            System.out.println("Mensaje de respuesta [getResponseMessage()]: "
                    + conexion.getResponseMessage());
            System.out
                    .println("Fecha última modificación [getLastModified()]: "
                            + new Date(conexion.getLastModified()));
            System.out.println("Tipo de contenido [getContentType()]: "
                    + conexion.getContentType());
            System.out.println("Codificación [getContentEncoding()]: "
                    + conexion.getContentEncoding());
            System.out.println("Tamaño del contenido [getContentLength()]: "
                    + conexion.getContentLength());
            System.out.println("Fecha expiración [getExpiration()]: "
                    + new Date(conexion.getExpiration()));
            // Se obtiene la respuesta, se lee línea y línea y se escribe en la
            // salida estándar.
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
