package es.iessaladillo.pedrojoya.crypto.hash;

import org.mindrot.jbcrypt.BCrypt;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public final class HashUtils {

    private static final String SHA_256_ALGORITHM = "SHA-256";
    private static final int SHA_256_SALT_SIZE_IN_BYTES = 16;

    private HashUtils() {
    }

    public static byte[] generateSHA256RandomSalt() {
        // Create a 16 bytes array for salt.
        byte[] salt = new byte[SHA_256_SALT_SIZE_IN_BYTES];
        // Fill the salt with secure random data and return it.
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(salt);
        return salt;
    }

    public static byte[] createSHA256Hash(String input)
            throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(SHA_256_ALGORITHM);
        return messageDigest.digest(input.getBytes());
    }

    public static byte[] createSHA256Hash(String input, byte[] salt) throws IOException, NoSuchAlgorithmException {
        byte[] bytesToHash = mixInputAndSalt(input, salt);
        MessageDigest messageDigest = MessageDigest.getInstance(SHA_256_ALGORITHM);
        return messageDigest.digest(bytesToHash);
    }

    public static byte[] createSHA256Hash2(String input, byte[] salt) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(SHA_256_ALGORITHM);
        messageDigest.update(salt);
        return messageDigest.digest(input.getBytes());
    }

    public static String BCryptHashPassword(String password) {
        // The salt is stored with the result string.
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean BCryptVerifyPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }

    private static byte[] mixInputAndSalt(String input, byte[] salt) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream.write(salt);
        byteArrayOutputStream.write(input.getBytes());
        return byteArrayOutputStream.toByteArray();
    }

}
