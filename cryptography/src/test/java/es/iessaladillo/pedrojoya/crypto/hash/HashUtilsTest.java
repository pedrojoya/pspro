//package es.iessaladillo.pedrojoya.crypto.hash;
//
//import org.junit.jupiter.api.Test;
//
//import javax.xml.bind.DatatypeConverter;
//import java.io.IOException;
//import java.security.NoSuchAlgorithmException;
//import java.util.Scanner;
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class HashUtilsTest {
//
//    @Test
//    void generateRandomSalt() {
//        byte[] salt = HashUtils.generateSHA256RandomSalt();
//        assertNotNull(salt);
//        System.out.println(DatatypeConverter.printHexBinary(salt));
//    }
//
//    @Test
//    void createSHA256Hash() throws IOException, NoSuchAlgorithmException {
//        byte[] salt = HashUtils.generateSHA256RandomSalt();
//        // The value to hash is some random uuid but it could be anything.
//        String valueToHash = UUID.randomUUID().toString();
//        // We create to hashes with the same value to hash and the same salt.
//        byte[] hash = HashUtils.createSHA256Hash2(valueToHash, salt);
//        assertNotNull(hash);
//        byte[] hash2 = HashUtils.createSHA256Hash(valueToHash, salt);
//        assertNotNull(hash2);
//        // We can't compare byte[] so we compare their hexBinary representation.
//        assertEquals(DatatypeConverter.printHexBinary(hash2), DatatypeConverter.printHexBinary(hash));
//        System.out.println(DatatypeConverter.printHexBinary(hash));
//    }
//
//    @Test
//    void hashPasswordAndVerify() {
//        Scanner scanner = new Scanner(System.in);
//        String signUpPassword = "Baldomero Llégate Ligero";
//        String hashedSignUpPassword = HashUtils.BCryptHashPassword(signUpPassword);
//        assertNotNull(hashedSignUpPassword);
//        System.out.println(hashedSignUpPassword);
//        String invalidLogInPassword = "Baldomerillo Llégate Ligero";
//        String hashedInvalidLogInPassword = HashUtils.BCryptHashPassword(invalidLogInPassword);
//        assertFalse(HashUtils.BCryptVerifyPassword(invalidLogInPassword, hashedSignUpPassword));
//        String validLogInPassword = "Baldomero Llégate Ligero";
//        assertTrue(HashUtils.BCryptVerifyPassword(validLogInPassword, hashedSignUpPassword));
//    }
//}