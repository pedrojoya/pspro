package es.iessaladillo.pedrojoya.completablefuture;

import java.util.concurrent.CompletableFuture;

public class ThenApplyAsync2Example {

    public static void main(String[] args) {
        new ThenApplyAsync2Example().thenApplyAsyncExample();
    }

    private void thenApplyAsyncExample() {
        CompletableFuture<Void> cf = CompletableFuture.supplyAsync(() ->
                generateNumber()
        ).thenApplyAsync(value ->
                duplicate(value)
        ).thenAcceptAsync(value ->
                printNumber(value)
        );
        System.out.printf("%s - Main\n", Thread.currentThread().getName());
        cf.join();
    }

    private int generateNumber() {
        System.out.printf("%s - Supplier\n", Thread.currentThread().getName());
        return 2;
    }

    private String duplicate(Integer value) {
        String duplicated = "value " + (value * 2);
        System.out.printf("%s - Function - Duplicated: %s\n",
                Thread.currentThread().getName(), duplicated);
        return duplicated;
    }

    private void printNumber(String value) {
        System.out.printf("%s - Consumer - %s\n",
                Thread.currentThread().getName(), value);
    }

}
