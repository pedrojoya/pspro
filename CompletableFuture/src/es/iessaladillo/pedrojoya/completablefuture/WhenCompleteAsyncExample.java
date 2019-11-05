package es.iessaladillo.pedrojoya.completablefuture;

import java.util.concurrent.CompletableFuture;

public class WhenCompleteAsyncExample {

    public static void main(String[] args) {
        new WhenCompleteAsyncExample().thenApplyAsyncExample();
    }

    private void thenApplyAsyncExample() {
        CompletableFuture<Void> cf =
                CompletableFuture.supplyAsync(this::generateNumber)
                        .thenApplyAsync(this::duplicate)
                        .thenAcceptAsync(this::printNumber);
        System.out.printf("%s - Main\n", Thread.currentThread().getName());
        cf.join();
    }

    private int generateNumber() {
        System.out.printf("%s - Supplier\n", Thread.currentThread().getName());
        return 2;
    }

    private Integer duplicate(Integer value) {
        int duplicated = value * 2;
        System.out.printf("%s - Function - Duplicated: %d\n",
                Thread.currentThread().getName(), duplicated);
        return duplicated;
    }

    private void printNumber(Integer value) {
        System.out.printf("%s - Consumer - %d\n",
                Thread.currentThread().getName(), value);
    }

}
