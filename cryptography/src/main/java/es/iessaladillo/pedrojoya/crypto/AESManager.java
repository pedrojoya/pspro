package es.iessaladillo.pedrojoya.crypto;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.*;

public class AESManager {

    private static final String AES_ALGORITHM = "AES";
    private static final String AES_CBC_CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final String AES_ECB_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
    private static final int KEY_SIZE_IN_BITS = 256;
    private static final int BITS_PER_BYTE = 8;
    private static final int INITIALIZATION_VECTOR_SIZE_IN_BITS = 16;

    public SecretKey generateKey() throws NoSuchAlgorithmException {
        SecureRandom secureRandom = new SecureRandom();
        // Get an instance of a key generator for AES
        KeyGenerator keyGenerator = KeyGenerator.getInstance(AES_ALGORITHM);
        // Initialize the key generator to generate a key of 256 bits using
        // a secureRandom as source of randomness
        keyGenerator.init(KEY_SIZE_IN_BITS, secureRandom);
        // Generate and return the key.
        return keyGenerator.generateKey();
    }

    public SecretKey createKeyFrom(byte[] password) {
        return new SecretKeySpec(password, 0, KEY_SIZE_IN_BITS / BITS_PER_BYTE, AES_ALGORITHM);
    }

    public void writeKeyInFile(Key key, File file) throws IOException {
        try(FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(key);
        }
    }

    public Key readKeyFromFile(File file) throws IOException, ClassNotFoundException {
        try(FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis)) {
            return (Key) ois.readObject();
        }
    }

    public byte[] generateInitializationVector() {
        byte[] initializationVector = new byte[INITIALIZATION_VECTOR_SIZE_IN_BITS];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(initializationVector);
        return initializationVector;
    }

    public byte[] encrypt(byte[] plainText, Key key, byte[] initializationVector) throws NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException, InvalidKeyException {
        Cipher cipher = Cipher.getInstance(AES_CBC_CIPHER_ALGORITHM);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(initializationVector);
        cipher.init(Cipher.ENCRYPT_MODE, key, ivParameterSpec);
        return cipher.doFinal(plainText);
    }

    public byte[] decrypt(byte[] cipherText, Key key, byte[] initializationVector) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(AES_CBC_CIPHER_ALGORITHM);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(initializationVector);
        cipher.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);
        return cipher.doFinal(cipherText);
    }

    public byte[] encrypt(byte[] plainText, Key key) throws NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException {
        Cipher cipher = Cipher.getInstance(AES_ECB_CIPHER_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(plainText);
    }

    public byte[] decrypt(byte[] cipherText, Key key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(AES_ECB_CIPHER_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(cipherText);
    }

}
