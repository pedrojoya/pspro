package es.iessaladillo.pedrojoya.invokeany;

import java.util.concurrent.Callable;

class AuthenticationTask implements Callable<AuthenticationSystem> {

    private final AuthenticationSystem authenticationSystem;
    private final String username;
    private final String password;

    AuthenticationTask(AuthenticationSystem authenticationSystem, String username, String password) {
        this.authenticationSystem = authenticationSystem;
        this.username = username;
        this.password = password;
    }

    @Override
    public AuthenticationSystem call() throws InterruptedException {
        boolean authenticated = authenticationSystem.authenticate(username, password);
        if (!authenticated) {
            throw new RuntimeException("Authentication failed");
        }
        return authenticationSystem;
    }

}
