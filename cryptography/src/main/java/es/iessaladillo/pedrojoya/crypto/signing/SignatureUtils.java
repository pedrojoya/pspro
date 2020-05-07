package es.iessaladillo.pedrojoya.crypto.signing;

import java.security.*;

public final class SignatureUtils {

    private static final String SIGNING_ALGORITHM = "SHA256withRSA";

    private SignatureUtils() {
    }

    public static byte[] createDigitalSignature(byte[] bytesToSign, PrivateKey privateKey) throws InvalidKeyException, NoSuchAlgorithmException, SignatureException {
        // Get signature instance for SHA256 with RSA algorithms
        Signature signature = Signature.getInstance(SIGNING_ALGORITHM);
        // Init sign with private key
        signature.initSign(privateKey);
        // Give original text to signature
        signature.update(bytesToSign);
        // Sign and return result
        return signature.sign();
    }

    public static boolean verifyDigitalSignature(byte[] originalBytes, byte[] signatureToVerify, PublicKey publicKey) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        // Get signature instance for SHA256 with RSA algorithms
        Signature signature = Signature.getInstance(SIGNING_ALGORITHM);
        // Init verification with public key
        signature.initVerify(publicKey);
        // Give origintal text to signature
        signature.update(originalBytes);
        // Verify and return result
        return signature.verify(signatureToVerify);
    }

}