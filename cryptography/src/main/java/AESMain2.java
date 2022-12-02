import es.iessaladillo.pedrojoya.crypto.AESManager;

import javax.crypto.SecretKey;
import java.io.File;
import java.nio.file.Files;
import java.security.Key;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Scanner;

class AESMain2 {

    public static void main(String[] args) {
        AESManager aesManager = new AESManager();
        try {
            // Key generation
            SecretKey secretKey = aesManager.generateKey();


            Files.write(new File("quillo").toPath(), secretKey.getEncoded());
            byte[] readBytes = Files.readAllBytes(new File("quillo").toPath());

            System.out.println("Secret key: " + Base64.getEncoder().encodeToString(secretKey.getEncoded()));
            // Write key in file
            File keyFile = new File("key.secret");
            aesManager.writeKeyInFile(secretKey, keyFile);
            // Read key from file
            Key savedKey = aesManager.readKeyFromFile(keyFile);
            System.out.println("Secret key: " + Base64.getEncoder().encodeToString(savedKey.getEncoded()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}