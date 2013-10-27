package es.iessaladillo.pedrojoya.pspro.gar0309;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

public class ApachePost {

    public static void main(String[] args) {
        try {
            // Se crea la peticion POST.
            HttpPost peticion = new HttpPost(
                    "http://www.informaticasaladillo.es/mostrar.php");
            // Se crea la lista de par�metros.
            ArrayList<NameValuePair> parametros = new ArrayList<NameValuePair>();
            parametros.add(new BasicNameValuePair("Nombre", "Pedro"));
            parametros.add(new BasicNameValuePair("Apellidos", "Joya"));
            // Obtengo el contenido a enviar (entidad) a partir de los
            // par�metros y lo establezco como entidad de la petici�n.
            UrlEncodedFormEntity entidadPeticion = new UrlEncodedFormEntity(
                    parametros);
            peticion.setEntity(entidadPeticion);
            // Se obtiene un cliente HTTP y se realiza la petici�n, obteniendo
            // la respuesta.
            CloseableHttpClient cliente = HttpClients.createDefault();
            CloseableHttpResponse respuesta = cliente.execute(peticion);
            // Se muestra por pantalla la respuesta, que se lee l�nea a l�nea.
            HttpEntity entidadRespuesta = respuesta.getEntity();
            BufferedReader lector = new BufferedReader(new InputStreamReader(
                    entidadRespuesta.getContent()));
            String linea;
            while ((linea = lector.readLine()) != null) {
                System.out.println(linea);
            }
            respuesta.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
