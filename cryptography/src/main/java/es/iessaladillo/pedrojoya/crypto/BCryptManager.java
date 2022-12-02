package es.iessaladillo.pedrojoya.crypto;

import org.mindrot.jbcrypt.BCrypt;

public class BCryptManager {

    public String hashPassword(String password) {
        // The salt is stored with the result string.
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean verifyPassword(String password, String hashedPassword) {
        return BCrypt.checkpw(password, hashedPassword);
    }

}
