import es.iessaladillo.pedrojoya.crypto.AESManager;

import javax.crypto.SecretKey;
import java.nio.file.Files;
import java.util.Base64;
import java.util.Scanner;

class AESMain {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        AESManager aesManager = new AESManager();
        String password = "Baldomero llegate ligero que nos lo vamos a pasar muy bien";
        try {
            // Key generation
            SecretKey secretKey = aesManager.createKeyFrom(password.getBytes());
            System.out.println("Secret key: " + Base64.getEncoder().encodeToString(secretKey.getEncoded()));

            // Encription
            byte[] initializationVector = aesManager.generateInitializationVector();
            System.out.print("Enter message: ");
            String plainText = scanner.nextLine();
            byte[] cipherText = aesManager.encrypt(plainText.getBytes(), secretKey, initializationVector);
            System.out.println("Cyphered message: " + Base64.getEncoder().encodeToString(cipherText));

            // Decrypting
            SecretKey decryptingKey = aesManager.createKeyFrom(password.getBytes());
            System.out.println("Decrypting key: " + Base64.getEncoder().encodeToString(decryptingKey.getEncoded()));
            byte[] decryptedText = aesManager.decrypt(cipherText, decryptingKey, initializationVector);
            System.out.println("Decrypted message: " + new String(decryptedText));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}