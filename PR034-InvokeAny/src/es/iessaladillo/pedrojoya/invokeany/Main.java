package es.iessaladillo.pedrojoya.invokeany;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

class Main {

    public static void main(String[] args) {
        String username = "username";
        String password = "password";
        RemoteDatabase remoteDatabase = new RemoteDatabase();
        LocalDatabase localDatabase = new LocalDatabase();
        AuthenticationTask remoteDatabaseTask = new AuthenticationTask(remoteDatabase, username, password);
        AuthenticationTask localDatabaseTask = new AuthenticationTask(localDatabase, username, password);
        List<AuthenticationTask> tasks = new ArrayList<>();
        tasks.add(remoteDatabaseTask);
        tasks.add(localDatabaseTask);
        ThreadPoolExecutor fixedThreadPoolExecutor =
                (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        try {
            AuthenticationSystem authenticationSystem = fixedThreadPoolExecutor.invokeAny(tasks);
            System.out.printf("User authenticated by %s\n", authenticationSystem.getName());
        } catch (InterruptedException ignored) {
        } catch (ExecutionException e) {
            System.out.print("User not authenticated\n");
        } finally {
            fixedThreadPoolExecutor.shutdown();
        }
    }

}
