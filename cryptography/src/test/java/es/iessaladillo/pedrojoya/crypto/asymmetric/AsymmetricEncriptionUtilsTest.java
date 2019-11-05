package es.iessaladillo.pedrojoya.crypto.asymmetric;

import org.junit.jupiter.api.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.xml.bind.DatatypeConverter;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

class AsymmetricEncriptionUtilsTest {

    @Test
    void generateRSAKeyPair() throws NoSuchAlgorithmException {
        KeyPair keyPair = AsymmetricEncriptionUtils.generateRSAKeyPair();
        assertNotNull(keyPair);
        System.out.println("Private key: " + DatatypeConverter.printHexBinary(keyPair.getPrivate().getEncoded()));
        System.out.println("Public key: " + DatatypeConverter.printHexBinary(keyPair.getPublic().getEncoded()));
    }

    @Test
    void decryptWithRSA() throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchPaddingException {
        KeyPair keyPair = AsymmetricEncriptionUtils.generateRSAKeyPair();
        String plainText = "Baldomero Llégate Ligero";
        byte[] cipherText = AsymmetricEncriptionUtils.encryptWithRSA(plainText, keyPair.getPrivate());
        assertNotNull(cipherText);
        System.out.println(DatatypeConverter.printHexBinary(cipherText));
        String decryptedText = AsymmetricEncriptionUtils.decryptWithRSA(cipherText, keyPair.getPublic());
        assertEquals(plainText, decryptedText);
    }

}