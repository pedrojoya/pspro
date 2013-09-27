package es.iessaladillo.pedrojoya.pspro.gar0105;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        // Se obtiene en entorno de ejecución.
        Runtime entorno = Runtime.getRuntime();
        String orden = "CMD /C DIR";
        try {
            // Se inicia el subproceso de listado del directorio de trabajo, que
            // se establece como C:/Windows.
            Process proceso = entorno.exec(orden, null, new File("C:/Windows"));
            // Se lee línea a línea del flujo proveniente del
            // subproceso y se muestra en pantalla.
            InputStream entrada = proceso.getInputStream();
            BufferedReader lector = new BufferedReader(new InputStreamReader(
                    entrada));
            String linea = lector.readLine();
            while (linea != null) {
                System.out.println(linea);
                linea = lector.readLine();
            }
            entrada.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
