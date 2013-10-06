import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Main {

    public static void main(String[] args) {
        // Se crea y configura el lanzador de procesos.
        ProcessBuilder lanzador = new ProcessBuilder("java", "Factorial");
        lanzador.directory(new File("bin"));
        try {
            // Se llama la subproceso.
            Process subproceso = lanzador.start();
            // Se le pasa el valor.
            PrintWriter escritor = new PrintWriter(subproceso.getOutputStream());
            escritor.println("5");
            escritor.close();
            // Se recibe la respuesta.
            BufferedReader lector = new BufferedReader(new InputStreamReader(
                    subproceso.getInputStream()));
            String respuesta = lector.readLine();
            while (respuesta != null) {
                System.out.println(respuesta);
                respuesta = lector.readLine();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
