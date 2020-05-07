package es.iessaladillo.pedrojoya.crypto.signing;

import es.iessaladillo.pedrojoya.crypto.asymmetric.AsymmetricEncryptionUtils;
import org.junit.jupiter.api.Test;

import javax.xml.bind.DatatypeConverter;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SignatureUtilsTest {

    @Test
    void verifyDigitalSignature() throws NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, InvalidKeyException {
        String originalText = "Baldomero Llégate Ligero";
        KeyPair keyPair = AsymmetricEncryptionUtils.generateRSAKeyPair();
        byte[] signature = SignatureUtils.createDigitalSignature(originalText.getBytes(), keyPair.getPrivate());
        assertNotNull(signature);
        System.out.println(DatatypeConverter.printHexBinary(signature));
        assertTrue(SignatureUtils.verifyDigitalSignature(originalText.getBytes(), signature, keyPair.getPublic()));
    }

}