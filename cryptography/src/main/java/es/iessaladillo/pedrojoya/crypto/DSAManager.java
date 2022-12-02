package es.iessaladillo.pedrojoya.crypto;

import java.security.*;

public final class DSAManager {

    private static final String DSA = "DSA";
    private static final int DSA_KEY_SIZE_IN_BITS = 1024;

    private static final String SIGNING_ALGORITHM = "SHA1withDSA";

    public KeyPair generateKeyPair() throws NoSuchAlgorithmException {
        SecureRandom secureRandom = new SecureRandom();
        // Get a KeyPairGenerator for DSA.
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(DSA);
        // Initialize generator to generator a key pair of 1024 bits using
        // a SecureRandom as source of randomness.
        keyPairGenerator.initialize(DSA_KEY_SIZE_IN_BITS, secureRandom);
        // Generate and return the KeyPair
        return keyPairGenerator.generateKeyPair();
    }

    public byte[] sign(byte[] input, PrivateKey privateKey) throws InvalidKeyException, NoSuchAlgorithmException, SignatureException {
        // Get signer instance for signing algorithm
        Signature signer = Signature.getInstance(SIGNING_ALGORITHM);
        // Init sign with private key
        signer.initSign(privateKey);
        // Give input to signer
        signer.update(input);
        // Sign and return result
        return signer.sign();
    }

    public boolean verify(byte[] input, byte[] signature, PublicKey publicKey) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        // Get verifier instance for SHA256 with RSA algorithms
        Signature verifier = Signature.getInstance(SIGNING_ALGORITHM);
        // Init verification with public key
        verifier.initVerify(publicKey);
        // Give input to verifier
        verifier.update(input);
        // Verify and return result
        return verifier.verify(signature);
    }

}