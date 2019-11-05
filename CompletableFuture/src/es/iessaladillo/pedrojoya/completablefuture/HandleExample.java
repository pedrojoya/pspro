package es.iessaladillo.pedrojoya.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;

public class HandleExample {

    public static void main(String[] args) {
        new HandleExample().exceptionallyExample();
    }

    private void exceptionallyExample() {
        CompletableFuture<Void> cf =
                CompletableFuture.supplyAsync(this::generateNumber)
                        .exceptionally(this::recover)
                        .thenApplyAsync(this::duplicate)
                        .thenAcceptAsync(this::printNumber);
        System.out.printf("%s - Main\n", Thread.currentThread().getName());
        cf.join();
    }

    private int generateNumber() {
        System.out.printf("%s - Supplier\n", Thread.currentThread().getName());
        int value = ThreadLocalRandom.current().nextInt(10) + 1;
        if (value > 5) {
            throw new RuntimeException();
        } else {
            return value;
        }
    }

    private Integer recover(Throwable throwable) {
        int recoveringValue = 100;
        System.out.printf("%s - Recovering to %d\n", Thread.currentThread().getName(), recoveringValue);
        return recoveringValue;
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
