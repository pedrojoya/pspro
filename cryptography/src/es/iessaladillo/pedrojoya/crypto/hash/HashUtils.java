package es.iessaladillo.pedrojoya.crypto.hash;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public final class HashUtils {

    private static final String SHA_256_ALGORITHM = "SHA-256";

    private HashUtils() {
    }

    public static byte[] generateRandomSalt() {
        byte[] salt = new byte[16];
        SecureRandom secureRandom = new SecureRandom();
        // Generate random bytes.
        secureRandom.nextBytes(salt);
        return salt;
    }

    public static byte[] createSHA256Hash(String input, byte[] salt) throws IOException, NoSuchAlgorithmException {
        byte[] bytesToHash = mixInputAndSalt(input, salt);
        MessageDigest messageDigest = MessageDigest.getInstance(SHA_256_ALGORITHM);
        return messageDigest.digest(bytesToHash);
    }

    private static byte[] mixInputAndSalt(String input, byte[] salt) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream.write(salt);
        byteArrayOutputStream.write(input.getBytes());
        return byteArrayOutputStream.toByteArray();
    }


}
