package es.iessaladillo.pedrojoya.pspro.gar0308;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class ApacheGet {

    public static void main(String[] args) {
        try {
            // Se crea la URL.
            URI uri = new URIBuilder().setScheme("http")
                    .setHost("www.iessaladillo.es").setPath("/web/index.php")
                    .setParameter("option", "com_content")
                    .setParameter("view", "article")
                    .setParameter("id", "4:historia-del-centro")
                    .setParameter("catid", "2:datos-de-interes")
                    .setParameter("Itemid", "5").build();
            // Se crea la petici�n GET.
            HttpGet peticion = new HttpGet(uri);
            // Se crea el cliente HTTP mediante m�todo est�tico.
            CloseableHttpClient cliente = HttpClients.createDefault();
            // Se ejecuta la petici�n en el cliente, obteniendo la respuesta.
            CloseableHttpResponse respuesta = cliente.execute(peticion);
            // Se muestra la cabecera.
            System.out.println("CABECERA\n");
            System.out.println("Versi�n del protocolo: "
                    + respuesta.getStatusLine().getProtocolVersion());
            System.out.println("C�digo de respuesta: "
                    + respuesta.getStatusLine().getStatusCode());
            System.out.println("Mensaje de respuesta: "
                    + respuesta.getStatusLine().getReasonPhrase());
            HeaderIterator camposCabecera = respuesta.headerIterator();
            while (camposCabecera.hasNext()) {
                Header campo = (Header) camposCabecera.next();
                System.out.println(campo.getName() + " : " + campo.getValue());
            }
            // Se muestra el contenido.
            HttpEntity contenido = respuesta.getEntity();
            System.out.println("Tipo de contenido: "
                    + contenido.getContentType());
            System.out.println("Tama�o del contenido: "
                    + contenido.getContentLength());
            System.out.println("Codificaci�n: "
                    + contenido.getContentEncoding());
            System.out.println("\nCONTENIDO\n");
            BufferedReader lector = new BufferedReader(new InputStreamReader(
                    contenido.getContent()));
            String linea;
            while ((linea = lector.readLine()) != null) {
                System.out.println(linea);
            }
            lector.close();
            // String texto = EntityUtils.toString(contenido);
            // System.out.println(texto);
            respuesta.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
