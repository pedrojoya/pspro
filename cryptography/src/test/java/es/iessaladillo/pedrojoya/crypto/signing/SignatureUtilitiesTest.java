package es.iessaladillo.pedrojoya.crypto.signing;

import es.iessaladillo.pedrojoya.crypto.asymmetric.AsymmetricEncriptionUtils;
import org.junit.jupiter.api.Test;

import javax.xml.bind.DatatypeConverter;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import static org.junit.jupiter.api.Assertions.*;

class SignatureUtilitiesTest {

    @Test
    void verifyDigitalSignature() throws NoSuchAlgorithmException, InvalidKeySpecException, SignatureException, InvalidKeyException {
        String originalText = "Baldomero Llégate Ligero";
        KeyPair keyPair = AsymmetricEncriptionUtils.generateRSAKeyPair();
        byte[] signature = SignatureUtilities.createDigitalSignature(originalText.getBytes(), keyPair.getPrivate());
        assertNotNull(signature);
        System.out.println(DatatypeConverter.printHexBinary(signature));
        assertTrue(SignatureUtilities.verifyDigitalSignature(originalText.getBytes(), signature, keyPair.getPublic()));
    }

}