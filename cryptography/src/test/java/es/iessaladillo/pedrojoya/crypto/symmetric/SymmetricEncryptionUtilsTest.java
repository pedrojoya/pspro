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

class SymmetricEncryptionUtilsTest {

    @Test
    void createAESKey() throws NoSuchAlgorithmException {
        SecretKey secretKey = SymmetricEncryptionUtils.createAESKey();
        assertNotNull(secretKey);
        System.out.println(DatatypeConverter.printHexBinary(secretKey.getEncoded()));
    }

    @Test
    void decryptWithAES() throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, InvalidAlgorithmParameterException, NoSuchPaddingException {
        SecretKey secretKey = SymmetricEncryptionUtils.createAESKey();
        byte[] initializationVector = SymmetricEncryptionUtils.createInitiazationVector();
        assertNotNull(initializationVector);
        String plainText = "Baldomero Llégate Ligero";
        byte[] cipherText = SymmetricEncryptionUtils.encryptWithAES(plainText, secretKey, initializationVector);
        assertNotNull(cipherText);
        System.out.println(DatatypeConverter.printHexBinary(cipherText));
        String decryptedText = SymmetricEncryptionUtils.decryptWithAES(cipherText, secretKey, initializationVector);
        assertEquals(plainText, decryptedText);
    }

}