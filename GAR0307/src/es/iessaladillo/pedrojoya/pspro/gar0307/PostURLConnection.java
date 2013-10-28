package es.iessaladillo.pedrojoya.pspro.gar0307;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class PostURLConnection {

    public static void main(String[] args) {
        try {
            // Se obtiene el objeto URL.
            URL url = new URL("http://www.informaticasaladillo.es/mostrar.php");
            // Se abre la conexi�n.
            HttpURLConnection conexion = (HttpURLConnection) url
                    .openConnection();
            // Se establecen algunas propiedades de la cabecera de la petici�n.
            conexion.setRequestMethod("POST");
            conexion.addRequestProperty("Accept", "text/html");
            conexion.setRequestProperty("User-Agent",
                    "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)");
            // Se obtienen los datos de la cabecera de la petici�n y se muestran
            // por pantalla.
            System.out.println("CABECERA DE LA PETICI�N\n");
            System.out.println("M�todo de petici�n [getRequestMethod()]: "
                    + conexion.getRequestMethod());
            Map<String, List<String>> cabeceraPeticion = conexion
                    .getRequestProperties();
            for (Map.Entry<String, List<String>> campoPeticion : cabeceraPeticion
                    .entrySet()) {
                System.out.println(campoPeticion.getKey() + " : "
                        + campoPeticion.getValue());
            }
            // Se env�an datos por el m�todo POST.
            conexion.setDoOutput(true);
            PrintWriter escritor = new PrintWriter(conexion.getOutputStream());
            String datos = "Nombre=Pedro&Apellido=Joya";
            escritor.print(datos);
            escritor.close();
            // Se muestra la cabecera de la respuesta.
            System.out.println("\nCABECERA DE LA RESPUESTA\n");
            Map<String, List<String>> cabeceraRespuesta = conexion
                    .getHeaderFields();
            for (Map.Entry<String, List<String>> campoRespuesta : cabeceraRespuesta
                    .entrySet()) {
                System.out.println(campoRespuesta.getKey() + " : "
                        + campoRespuesta.getValue());
            }
            // Se obtiene la respuesta, se lee l�nea y l�nea y se escribe en la
            // salida est�ndar.
            System.out.println("\nCONTENIDO DE LA RESPUESTA\n");
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
