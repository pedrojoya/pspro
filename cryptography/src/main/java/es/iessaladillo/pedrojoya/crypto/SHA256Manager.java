package es.iessaladillo.pedrojoya.crypto;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class SHA256Manager {

    private static final String SHA_256_ALGORITHM = "SHA-256";
    private static final int SHA_256_SALT_SIZE_IN_BYTES = 16;

    public byte[] createHash(byte[] input)
            throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(SHA_256_ALGORITHM);
        return messageDigest.digest(input);
    }

    public byte[] createHash(byte[] input, byte[] salt) throws IOException, NoSuchAlgorithmException {
        byte[] bytesToHash = mixInputAndSalt(input, salt);
        MessageDigest messageDigest = MessageDigest.getInstance(SHA_256_ALGORITHM);
        return messageDigest.digest(bytesToHash);
    }

    public byte[] generateSalt() {
        // Create a 16 bytes array for salt.
        byte[] salt = new byte[SHA_256_SALT_SIZE_IN_BYTES];
        // Fill the salt with secure random data and return it.
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(salt);
        return salt;
    }

    public String toHexString(byte[] input) {
        return String.format("%064x", new BigInteger(1, input));
    }

    private byte[] mixInputAndSalt(byte[] input, byte[] salt) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream.write(salt);
        byteArrayOutputStream.write(input);
        return byteArrayOutputStream.toByteArray();
    }

}
