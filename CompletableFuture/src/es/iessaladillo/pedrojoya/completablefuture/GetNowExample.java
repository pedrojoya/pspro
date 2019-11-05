package es.iessaladillo.pedrojoya.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class GetNowExample {

    public static void main(String[] args) {
        new GetNowExample().suplyAsyncExample();
    }

    private void suplyAsyncExample() {
        CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> generateNumber());
        try {
            Integer generatedValue = cf.get();
            System.out.printf("%s - Main - Generated: %d\n", Thread.currentThread().getName(), generatedValue);
        } catch (InterruptedException ignored) {
        } catch (ExecutionException e) {
            System.out.println("Exception thrown in async task");
        }
    }

    private int generateNumber() {
        System.out.printf("%s - Task\n", Thread.currentThread().getName());
        return 2;
    }

}
