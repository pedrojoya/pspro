package es.iessaladillo.pedrojoya.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;

public class HandleExample {

    public static void main(String[] args) {
        new HandleExample().handleExample();
    }

    private void handleExample() {
        CompletableFuture<Void> cf =
                CompletableFuture.supplyAsync(this::generateNumber)
                        .handle(this::handler)
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

    private Integer handler(Integer value, Throwable throwable) {
        Integer valueToReturn = value;
        if (throwable != null) {
            valueToReturn = 100;
        }
        System.out.printf("%s - Handler returning %d\n", Thread.currentThread().getName(), valueToReturn);
        return valueToReturn;
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
