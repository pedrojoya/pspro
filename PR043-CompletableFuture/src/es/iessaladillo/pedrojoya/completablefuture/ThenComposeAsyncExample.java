package es.iessaladillo.pedrojoya.completablefuture;

import java.util.concurrent.CompletableFuture;

public class ThenComposeAsyncExample {

    public static void main(String[] args) {
        new ThenComposeAsyncExample().thenComposeAsyncExample();
    }

    private void thenComposeAsyncExample() {
        CompletableFuture<Void> cf =
                CompletableFuture.supplyAsync(this::generateNumber)
                        .thenComposeAsync(this::duplicate)
                        .thenAcceptAsync(this::printNumber);
        System.out.printf("%s - Main\n", Thread.currentThread().getName());
        cf.join();
    }

    private int generateNumber() {
        System.out.printf("%s - Supplier\n", Thread.currentThread().getName());
        return 2;
    }

    private CompletableFuture<Integer> duplicate(Integer value) {
        return CompletableFuture.supplyAsync(() -> {
            int duplicated = value * 2;
            System.out.printf("%s - Function - Duplicated: %d\n",
                    Thread.currentThread().getName(), duplicated);
            return duplicated;
        });
    }

    private void printNumber(Integer value) {
        System.out.printf("%s - Consumer - %d\n",
                Thread.currentThread().getName(), value);
    }

}
