package es.iessaladillo.pedrojoya.invokeany;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class LocalDatabase implements AuthenticationSystem {

    @Override
    public String getName() {
        return "Local database";
    }

    @SuppressWarnings("unused")
    @Override
    public boolean authenticate(String username, String password) throws InterruptedException {
        int searchDuration = ThreadLocalRandom.current().nextInt(5) + 1;
        System.out.print("Local database -> Authenticating...\n");
        search(searchDuration);
        boolean authenticated = ThreadLocalRandom.current().nextBoolean();
        if (authenticated) {
            System.out.printf("Local database -> Authenticated in %d seconds\n", searchDuration);
        } else {
            System.out.printf("Local database -> Not authenticated in %d seconds\n", searchDuration);
        }
        return authenticated;
    }

    private void search(int searchDuration) throws InterruptedException {
        try {
            TimeUnit.SECONDS.sleep(searchDuration);
        } catch (InterruptedException e) {
            System.out.print("Local database -> Authentication cancelled\n");
            throw new InterruptedException();
        }
    }

}
