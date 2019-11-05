package es.iessaladillo.pedrojoya;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Parrot {

    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                System.in))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading from parent process");
        }
    }

}
