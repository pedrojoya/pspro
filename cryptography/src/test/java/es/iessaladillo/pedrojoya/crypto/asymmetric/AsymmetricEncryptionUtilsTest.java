//package es.iessaladillo.pedrojoya.crypto.asymmetric;
//
//import org.junit.jupiter.api.Test;
//
//import javax.crypto.BadPaddingException;
//import javax.crypto.IllegalBlockSizeException;
//import javax.crypto.NoSuchPaddingException;
//import javax.xml.bind.DatatypeConverter;
//import java.security.InvalidKeyException;
//import java.security.KeyPair;
//import java.security.NoSuchAlgorithmException;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//class AsymmetricEncryptionUtilsTest {
//
//    @Test
//    void generateRSAKeyPair() throws NoSuchAlgorithmException {
//        KeyPair keyPair = AsymmetricEncryptionUtils.generateRSAKeyPair();
//        assertNotNull(keyPair);
//        System.out.println("Private key: " + DatatypeConverter.printHexBinary(keyPair.getPrivate().getEncoded()));
//        System.out.println("Public key: " + DatatypeConverter.printHexBinary(keyPair.getPublic().getEncoded()));
//    }
//
//    @Test
//    void decryptWithRSA() throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchPaddingException {
//        KeyPair keyPair = AsymmetricEncryptionUtils.generateRSAKeyPair();
//        String plainText = "Baldomero Llégate Ligero";
//        byte[] cipherText = AsymmetricEncryptionUtils.encryptWithRSA(plainText, keyPair.getPrivate());
//        assertNotNull(cipherText);
//        System.out.println(DatatypeConverter.printHexBinary(cipherText));
//        String decryptedText = AsymmetricEncryptionUtils.decryptWithRSA(cipherText, keyPair.getPublic());
//        assertEquals(plainText, decryptedText);
//    }
//
//}