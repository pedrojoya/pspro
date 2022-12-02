import es.iessaladillo.pedrojoya.crypto.AESManager;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

class ChipherStreamsMain {

    private static final String AES_ECB_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

    public static void main(String[] args) {
        AESManager aesManager = new AESManager();
        String password = "Baldomero llegate ligero que nos lo vamos a pasar muy bien";
        String originalText = "No te digo trigo por no llamarte Rodrigo";
        SecretKey secretKey = aesManager.createKeyFrom(password.getBytes());
        try {
            File originalFile = new File("original.txt");
            File encryptedFile = new File("encrypted.txt");
            File decryptedFile = new File("decrypted.txt");
            Files.write(originalFile.toPath(), originalText.getBytes());
            String originalContent = new String(Files.readAllBytes(originalFile.toPath()));
            System.out.printf("Original: %s\n", originalContent);
            // Encrypt file
            Cipher encrypter = Cipher.getInstance(AES_ECB_CIPHER_ALGORITHM);
            encrypter.init(Cipher.ENCRYPT_MODE, secretKey);
            try(FileInputStream fis = new FileInputStream(originalFile);
                FileOutputStream fos = new FileOutputStream(encryptedFile);
                CipherOutputStream cos = new CipherOutputStream(fos, encrypter)) {
                byte[] buffer = new byte[encrypter.getBlockSize()];
                int bytesRead = fis.read(buffer);
                while (bytesRead != -1) {
                    cos.write(buffer, 0, bytesRead);
                    bytesRead = fis.read(buffer);
                }
                cos.flush();
            }
            String encryptedContent = Base64.getEncoder().encodeToString(Files.readAllBytes(encryptedFile.toPath()));
            System.out.printf("Encrypted: %s\n", encryptedContent);
            // Decrypt file
            Cipher decrypter = Cipher.getInstance(AES_ECB_CIPHER_ALGORITHM);
            encrypter.init(Cipher.DECRYPT_MODE, secretKey);
            try(FileInputStream fis = new FileInputStream(encryptedFile);
                CipherInputStream cis = new CipherInputStream(fis, encrypter);
                FileOutputStream fos = new FileOutputStream(decryptedFile)) {
                byte[] buffer = new byte[decrypter.getBlockSize()];
                int bytesRead = cis.read(buffer);
                while (bytesRead != -1) {
                    fos.write(buffer, 0, bytesRead);
                    bytesRead = cis.read(buffer);
                }
                fos.flush();
            }
            String decryptedContent = new String(Files.readAllBytes(decryptedFile.toPath()));
            System.out.printf("Decrypted: %s\n", decryptedContent);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}