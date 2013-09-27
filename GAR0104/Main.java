import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        // Se crea el constructor de procesos para ejecutar un fichero BAT
        // recibido por la entrada estándar.
        ProcessBuilder pb = new ProcessBuilder("CMD");
        // Se redireccionan los flujos de entrada a los archivos
        // correspondientes.
        File entrada = new File("D:/entrada.bat");
        File salida = new File("D:/salida.txt");
        File error = new File("D:/error.txt");
        pb.redirectInput(entrada);
        pb.redirectOutput(salida);
        pb.redirectError(error);
        // Se inicia el subproceso.
        try {
            pb.start();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
