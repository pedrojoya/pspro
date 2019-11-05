package es.iessaladillo.pedrojoya.invokeany;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Ldap implements AuthenticationSystem {

    @Override
    public boolean authenticate(String username, String password) throws InterruptedException {
        int searchDuration = ThreadLocalRandom.current().nextInt(5) + 1;
        System.out.print("Ldap -> Authenticating...\n");
        search(searchDuration);
        boolean found = ThreadLocalRandom.current().nextBoolean();
        if (found) {
            System.out.printf("Ldap -> Found in %d seconds\n", searchDuration);
        } else {
            System.out.printf("Ldap -> Not found in %d seconds\n", searchDuration);
        }
        return found;
    }

    private void search(int searchDuration) throws InterruptedException {
        TimeUnit.SECONDS.sleep(searchDuration);
    }

}
