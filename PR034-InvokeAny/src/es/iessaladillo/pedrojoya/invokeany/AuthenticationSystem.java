package es.iessaladillo.pedrojoya.invokeany;

public interface AuthenticationSystem {
    String getName();
    @SuppressWarnings("unused")
    boolean authenticate(String username, String password) throws InterruptedException;
}
