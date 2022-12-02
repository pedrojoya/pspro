import es.iessaladillo.pedrojoya.crypto.AESManager;
import es.iessaladillo.pedrojoya.crypto.RSAManager;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.Scanner;

public class SessionKeyMain {

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, InvalidAlgorithmParameterException {
        Scanner scanner = new Scanner(System.in);
        AESManager aesManager = new AESManager();
        RSAManager rsaManager = new RSAManager();

        // Simulation of receiver KeyPair.
        KeyPair receiverKeyPair = rsaManager.generateKeyPair();
        byte[] receiverPublicKeyBytes = receiverKeyPair.getPublic().getEncoded();
        byte[] receiverPrivateKeyBytes = receiverKeyPair.getPrivate().getEncoded();
        // End simulation

        // Sender generates SecretKey
        SecretKey secretKey = aesManager.generateKey();

        // Sender encrypts SecretKey using receiver public key and sends to result to receiver.
        PublicKey receiverPublicKey = rsaManager.createPublicKey(receiverPublicKeyBytes);
        byte[] wrappedKeyBytes = rsaManager.wrapKey(secretKey, receiverPublicKey);

        // Receiver receives encrypted SecretKey and decrypts it using its private key.
        PrivateKey receiverPrivateKey = rsaManager.createPrivateKey(receiverPrivateKeyBytes);
        Key receivedSecretKey = rsaManager.unwrapAESSecretKey(wrappedKeyBytes, receiverPrivateKey);

        // Sender encrypts message using SecretKey.
        System.out.println("Enter message: ");
        String plainText = scanner.nextLine();
        byte[] encryptedMessage = aesManager.encrypt(plainText.getBytes(), receivedSecretKey);

        // Receiver receives encryptedmessage end decrypts is using SecretKey.
        byte[] decryptedMessage = aesManager.decrypt(encryptedMessage, receivedSecretKey);
        System.out.printf("Receiver received: %s\n", new String(decryptedMessage));

    }

}
