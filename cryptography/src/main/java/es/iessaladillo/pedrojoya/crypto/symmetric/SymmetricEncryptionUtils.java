package es.iessaladillo.pedrojoya.crypto.symmetric;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class SymmetricEncryptionUtils {

    private static final String AES_ALGORITHM = "AES";
    private static final String AES_CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final int KEY_SIZE_IN_BITS = 256;
    private static final int INITIALIZATION_VECTOR_SIZE_IN_BITS = 16;

    private SymmetricEncryptionUtils() {
    }

    public static SecretKey createAESKey() throws NoSuchAlgorithmException {
        SecureRandom secureRandom = new SecureRandom();
        // Get an instance of a key generator for AES
        KeyGenerator keyGenerator = KeyGenerator.getInstance(AES_ALGORITHM);
        // Initialize the key generator to generate a key of 256 bits using
        // a secureRandom as source of randomness
        keyGenerator.init(KEY_SIZE_IN_BITS, secureRandom);
        // Generate and return the key.
        return keyGenerator.generateKey();
    }

    public static byte[] createInitiazationVector() {
        byte[] initializationVector = new byte[INITIALIZATION_VECTOR_SIZE_IN_BITS];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(initializationVector);
        return initializationVector;
    }

    public static byte[] encryptWithAES(String plainText, SecretKey secretKey, byte[] initializationVector) throws NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException, InvalidKeyException {
        Cipher cipher = Cipher.getInstance(AES_CIPHER_ALGORITHM);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(initializationVector);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
        return cipher.doFinal(plainText.getBytes());
    }

    public static String decryptWithAES(byte[] cipherText, SecretKey secretKey, byte[] initializationVector) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(AES_CIPHER_ALGORITHM);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(initializationVector);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
        byte[] result = cipher.doFinal(cipherText);
        return new String(result);
    }

}
