package es.iessaladillo.pedrojoya.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ThenAcceptExample {

    public static void main(String[] args) {
        new ThenAcceptExample().thenAcceptExample();
    }

    private void thenAcceptExample() {
        CompletableFuture.supplyAsync(() ->
                generateNumber()
        ).thenAccept(value ->
                printNumber(value)
        );
        System.out.printf("%s - Main\n", Thread.currentThread().getName());
    }

    private int generateNumber() {
        System.out.printf("%s - Supplier\n", Thread.currentThread().getName());
        return 2;
    }

    private void printNumber(Integer generatedValue) {
        System.out.printf("%s - Consumer - Generated: %d\n",
                Thread.currentThread().getName(), generatedValue);
    }

}
