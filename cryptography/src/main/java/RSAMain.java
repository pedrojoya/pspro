import es.iessaladillo.pedrojoya.crypto.RSAManager;

import java.io.File;
import java.security.KeyPair;
import java.security.PublicKey;
import java.util.Base64;
import java.nio.file.Files;

class RSAMain {

    public static void main(String[] args) {
        RSAManager rsaManager = new RSAManager();
        try {
            // Generation
            KeyPair keyPair = rsaManager.generateKeyPair();
            byte[] publicKeyBytes = keyPair.getPublic().getEncoded();
            System.out.println("\nPublic key: " + Base64.getEncoder().encodeToString(publicKeyBytes));

            // Save public key
            File publicKeyFile = new File("key.public");
            byte[] exportedPublicKey = rsaManager.exportPublicKey(keyPair.getPublic());
            Files.write(publicKeyFile.toPath(), exportedPublicKey);

            // Create public key from file
            byte[] readBytes = Files.readAllBytes(publicKeyFile.toPath());
            PublicKey key = rsaManager.createPublicKey(readBytes);
            System.out.println("\nPublic key: " + Base64.getEncoder().encodeToString(key.getEncoded()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}