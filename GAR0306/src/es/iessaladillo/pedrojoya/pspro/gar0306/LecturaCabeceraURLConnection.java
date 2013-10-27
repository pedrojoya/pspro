package es.iessaladillo.pedrojoya.pspro.gar0306;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class LecturaCabeceraURLConnection {

    public static void main(String[] args) {
        // Se obtiene el objeto URL.
        URL url = null;
        try {
            url = new URL("http://getbootstrap.com");
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // Se abre la conexi�n y se hace a HttpURLConnection, ya que es una
        // conexi�n HTTP.
        try {
            HttpURLConnection conexion = (HttpURLConnection) url
                    .openConnection();
            // Se obtienen los datos de la cabecera y se muestran por pantalla.
            // Campos con m�todo get propio.
            System.out.println("CAMPOS CON M�TODO GET PROPIO\n");
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
            // Todos los campos de la cabecera.
            System.out
                    .println("\nTODOS LOS CAMPOS DE LA CABECERA [getHeaderFields()]\n");
            Map<String, List<String>> camposCabecera = conexion
                    .getHeaderFields();
            for (Map.Entry<String, List<String>> campo : camposCabecera
                    .entrySet()) {
                System.out.println(campo.getKey() + " : " + campo.getValue());
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
