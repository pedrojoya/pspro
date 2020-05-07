package es.iessaladillo.pedrojoya.crypto.asymmetric;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;

public class AsymmetricEncryptionUtils {

    private static final String RSA = "RSA";
    private static final int RSA_KEY_SIZE_IN_BITS = 4096;

    public static KeyPair generateRSAKeyPair() throws NoSuchAlgorithmException {
        SecureRandom secureRandom = new SecureRandom();
        // Get a KeyPairGenerator for RSA.
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA);
        // Initialize generator to generator a key pair of 4096 bits using
        // a SecureRandom as source of randomness.
        keyPairGenerator.initialize(RSA_KEY_SIZE_IN_BITS, secureRandom);
        // Generate and return the KeyPair
        return keyPairGenerator.generateKeyPair();
    }

    public static byte[] encryptWithRSA(String plainText, PrivateKey privateKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        // Get cipher for RSA.
        Cipher cipher = Cipher.getInstance(RSA);
        // Initialize it in encrypt mode with the private key.
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        // Return the plainText encripted.
        return cipher.doFinal(plainText.getBytes());
    }

    public static String decryptWithRSA(byte[] cipherText, PublicKey publicKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        // Get cipher for RSA.
        Cipher cipher = Cipher.getInstance(RSA);
        // Initialize it in decrypt mode with the public key.
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        // Decrypt the message and return the plain text
        byte[] result = cipher.doFinal(cipherText);
        return new String(result);
    }

}
