package es.iessaladillo.pedrojoya.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;

public class ThenAcceptBothAsyncExample {

    public static void main(String[] args) {
        new ThenAcceptBothAsyncExample().thenAcceptBothAsyncExample();
    }

    private void thenAcceptBothAsyncExample() {
        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(this::generateNumber1);
        CompletableFuture<Integer> cf2 = CompletableFuture.supplyAsync(this::generateNumber2);
        CompletableFuture<Void> cfBothConsumed = cf1.thenAcceptBothAsync(cf2, this::add);
        System.out.printf("%s - Main\n", Thread.currentThread().getName());
        cfBothConsumed.join();
    }

    private int generateNumber1() {
        int value = ThreadLocalRandom.current().nextInt(5) + 1;
        sleep(value * 1000);
        System.out.printf("%s - Supplier1 - %d\n", Thread.currentThread().getName(), value);
        return value;
    }

    private int generateNumber2() {
        int value = ThreadLocalRandom.current().nextInt(5) + 1;
        sleep(value * 1000);
        System.out.printf("%s - Supplier2 - %d\n", Thread.currentThread().getName(), value);
        return value;
    }

    private void add(Integer value1, Integer value2) {
        System.out.printf("%s - BiConsumer - %d + %d = %d\n",
                Thread.currentThread().getName(), value1, value2, value1 + value2);
    }

    private boolean sleep(long timeInMillis) {
        try {
            Thread.sleep(timeInMillis);
            return true;
        } catch (InterruptedException e) {
            return false;
        }
    }

}
