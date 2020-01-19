package es.iessaladillo.pedrojoya.completablefuture;

import java.util.concurrent.CompletableFuture;

public class ThenAcceptAsyncExample {

    public static void main(String[] args) {
        new ThenAcceptAsyncExample().thenAcceptAsyncExample();
    }

    private void thenAcceptAsyncExample() {
        CompletableFuture<Void> cf =
                CompletableFuture.supplyAsync(this::generateNumber)
                        .thenAcceptAsync(this::printNumber);
        System.out.printf("%s - Main\n", Thread.currentThread().getName());
        cf.join();
    }

    private int generateNumber() {
        System.out.printf("%s - Supplier\n", Thread.currentThread().getName());
        return 2;
    }

    private void printNumber(Integer generatedValue) {
        System.out.printf("%s - Consumer - %d\n",
                Thread.currentThread().getName(), generatedValue);
    }

}
