import java.io.*;

public class Main {

    public static void main(String[] args) {
        String command = "ls -ltra /Library";
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        Process process = null;
        try {
            process = processBuilder.start();
        } catch (IOException e) {
            System.out.printf("Error trying to execute %s", command);
            return;
        }
        System.out.println("\nReceiving from command...");
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                process.getInputStream()))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error receiving data from command");
        }
    }

}