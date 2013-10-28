package es.iessaladillo.pedrojoya.pspro.gar0306;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class LecturaCabeceraURLConnection {

    public static void main(String[] args) {
        try {
            // Se obtiene el objeto URL.
            URL url = new URL("http://getbootstrap.com");
            // Se abre la conexión y se hace a HttpURLConnection, ya que es una
            // conexión HTTP.
            HttpURLConnection conexion = (HttpURLConnection) url
                    .openConnection();
            // Se obtienen los datos de la cabecera de la respuesta y se
            // muestran por pantalla.
            // Campos con método get propio.
            System.out.println("\nCABECERA DE LA RESPUESTA\n");
            System.out.println("CAMPOS CON MÉTODO GET PROPIO\n");
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
            // Todos los campos de la cabecera.
            System.out
                    .println("\nTODOS LOS CAMPOS DE LA CABECERA DE LA RESPUESTA [getHeaderFields()]\n");
            Map<String, List<String>> cabeceraRespuesta = conexion
                    .getHeaderFields();
            for (Map.Entry<String, List<String>> campo : cabeceraRespuesta
                    .entrySet()) {
                System.out.println(campo.getKey() + " : " + campo.getValue());
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
