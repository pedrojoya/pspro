package es.iessaladillo.pedrojoya.crypto.signing;

import java.security.*;

public final class SignatureUtilities {

    private static final String SIGNING_ALGORITHM = "SHA256withRSA";

    private SignatureUtilities() {
    }

    public byte[] createDigitalSignature(byte[] bytesToSign, PrivateKey privateKey) throws InvalidKeyException, NoSuchAlgorithmException, SignatureException {
        Signature signature = Signature.getInstance(SIGNING_ALGORITHM);
        signature.initSign(privateKey);
        signature.update(bytesToSign);
        return signature.sign();
    }

    public boolean verifyDigitalSignature(byte[] originalBytes, byte[] signatureToVerify, PublicKey publicKey) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature signature = Signature.getInstance(SIGNING_ALGORITHM);
        signature.initVerify(publicKey);
        signature.update(originalBytes);
        return signature.verify(signatureToVerify);
    }
    
}