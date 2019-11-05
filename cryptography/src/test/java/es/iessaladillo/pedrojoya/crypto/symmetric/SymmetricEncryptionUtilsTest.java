package es.iessaladillo.pedrojoya.crypto.symmetric;

import org.junit.jupiter.api.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

class SymmetricUtilsTest {

    @Test
    void createAESKey() throws NoSuchAlgorithmException {
        SecretKey secretKey = SymmetricUtils.createAESKey();
        assertNotNull(secretKey);
        System.out.println(DatatypeConverter.printHexBinary(secretKey.getEncoded()));
    }

    @Test
    void decryptWithAES() throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, InvalidAlgorithmParameterException, NoSuchPaddingException {
        SecretKey secretKey = SymmetricUtils.createAESKey();
        byte[] initializationVector = SymmetricUtils.createInitiazationVector();
        assertNotNull(initializationVector);
        String plainText = "Baldomero Llégate Ligero";
        byte[] cipherText = SymmetricUtils.encryptWithAES(plainText, secretKey, initializationVector);
        System.out.println(DatatypeConverter.printHexBinary(cipherText));
        String result = SymmetricUtils.decryptWithAES(cipherText, secretKey, initializationVector);
        assertEquals(plainText, result);
    }
}