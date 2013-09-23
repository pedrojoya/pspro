import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Lorito {

    public static void main(String[] args) {
        // Se lee una l�nea de la entrada est�ndar y se escribe en la salida
        // est�ndar.
        BufferedReader lector = new BufferedReader(new InputStreamReader(
                System.in));
        try {
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
