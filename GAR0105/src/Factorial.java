import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Factorial {

    public static void main(String[] args) {
        // Se lee de la entrada estándar el valor al que calcular el factorial.
        BufferedReader lector = new BufferedReader(new InputStreamReader(
                System.in));
        try {
            String sValor = lector.readLine();
            int valor = Integer.parseInt(sValor);
            // Se calcula el factorial.
            int fact = 1;
            for (int i = 2; i <= valor; i++) {
                fact *= i;
            }
            // Se escribe el resultado en la salida estandar.
            System.out.println(fact);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
