package es.iessaladillo.pedrojoya.crypto;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSAManager {

    private static final String RSA_ALGORITHM = "RSA";
    private static final int RSA_KEY_SIZE_IN_BITS = 4096;
    private static final String AES_ALGORITHM = "AES";

    public KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        SecureRandom secureRandom = new SecureRandom();
        // Get a KeyPairGenerator for RSA.
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA_ALGORITHM);
        // Initialize generator to generator a key pair of 4096 bits using
        // a SecureRandom as source of randomness.
        keyPairGenerator.initialize(RSA_KEY_SIZE_IN_BITS, secureRandom);
        // Generate and return the KeyPair
        return keyPairGenerator.generateKeyPair();
    }

    public PublicKey createPublicKey(byte[] input) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        KeySpec keySpec = new X509EncodedKeySpec(input);
        return keyFactory.generatePublic(keySpec);
    }

    public byte[] exportPublicKey(PublicKey key) {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(key.getEncoded());
        return keySpec.getEncoded();
    }

    public PrivateKey createPrivateKey(byte[] input) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        KeySpec keySpec = new PKCS8EncodedKeySpec(input);
        return keyFactory.generatePrivate(keySpec);
    }

    public byte[] encrypt(byte[] plainText, Key key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        // Get cipher for RSA.
        Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
        // Initialize it in encrypt mode with the key.
        cipher.init(Cipher.ENCRYPT_MODE, key);
        // Return the plainText encripted.
        return cipher.doFinal(plainText);
    }

    public byte[] wrapKey(Key unwrappedKey, Key key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException {
        // Get cipher for RSA.
        Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
        // Initialize it in encrypt mode with the key.
        cipher.init(Cipher.WRAP_MODE, key);
        // Return the unwrappedKey wrapped.
        return cipher.wrap(unwrappedKey);
    }

    public Key unwrapKey(byte[] wrappedKey, Key key, String wrappedKeyAlgorithm, int wrappedKeyType) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException {
        // Get cipher for RSA.
        Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
        // Initialize it in unwrap mode with the key.
        cipher.init(Cipher.UNWRAP_MODE, key);
        // Return the wrappedKey unwrapped.
        return cipher.unwrap(wrappedKey, wrappedKeyAlgorithm, wrappedKeyType);
    }

    public Key unwrapAESSecretKey(byte[] wrappedSecretKey, Key key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException {
        // Get cipher for RSA.
        Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
        // Initialize it in unwrap mode with the key.
        cipher.init(Cipher.UNWRAP_MODE, key);
        // Return the keyToWrap wrapped.
        return cipher.unwrap(wrappedSecretKey, AES_ALGORITHM, Cipher.SECRET_KEY);
    }

    public byte[] decrypt(byte[] cipherText, Key key) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        // Get cipher for RSA.
        Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
        // Initialize it in decrypt mode with the key.
        cipher.init(Cipher.DECRYPT_MODE, key);
        // Decrypt the message and return the plain text
        return cipher.doFinal(cipherText);
    }

}
