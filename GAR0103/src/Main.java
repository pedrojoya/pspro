import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

public class Main {

    public static void main(String[] args) {
        // Se obtiene el entorno de ejecuci�n.
        Runtime entorno = Runtime.getRuntime();
        String orden = "java Lorito";
        try {
            // Se ejecuta el proceso Lorito estableciendo como directorio de
            // trabajo donde est� Lorito.class (relativo al directorio del
            // proyecto)
            Process proceso = entorno.exec(orden, null, new File("bin"));
            // Se informa al usuario que va a comenzar el env�o.
            System.out.println("Enviando a Lorito...");
            // Se env�a el mensaje al programa Lorito.
            OutputStream salida = proceso.getOutputStream();
            String mensaje = "No te digo trigo por no llamarte Rodrigo\n";
            mensaje += "La cosa est� muy mala";
            PrintWriter escritor = new PrintWriter(salida);
            escritor.println(mensaje);
            escritor.close();
            // Se informa al usuario del mensaje enviado y que comienza la
            // lectura de la respuesta.
            System.out.println(mensaje);
            System.out.println("\nRecibido desde Lorito...");
            // Se lee del flujo proveniente del subproceso.
            BufferedReader lector = new BufferedReader(new InputStreamReader(
                    proceso.getInputStream()));
            String linea = lector.readLine();
            while (linea != null) {
                System.out.println(linea);
                linea = lector.readLine();
            }
            lector.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
