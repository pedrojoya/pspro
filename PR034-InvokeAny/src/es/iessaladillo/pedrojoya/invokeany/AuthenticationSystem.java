package es.iessaladillo.pedrojoya.invokeany;

public interface Authentication {
    boolean validate(String username, String password) throws InterruptedException;
}
