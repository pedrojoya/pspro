import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class Main {

    public static void main(String[] args) {
        // Se obtiene el entorno de ejecución.
        Runtime entorno = Runtime.getRuntime();
        String orden = "java Lorito";
        try {
            // Se ejecuta el proceso Lorito.
            Process proceso = entorno.exec(orden);
            // Se escribe el texto que recibirá Lorito.
            System.out.println("Enviando a Lorito...");
            OutputStream salida = proceso.getOutputStream();
            String mensaje = "No te digo trigo por no llamarte Rodrigo\n";
            mensaje += "La cosa está muy mala\n";
            System.out.print(mensaje);
            salida.write(mensaje.getBytes());
            salida.flush();
            // Leo del flujo proveniente del subproceso.
            System.out.println("\nRecibido desde Lorito...");
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
