package es.iessaladillo.pedrojoya.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;

public class RunEitherAsyncExample {

    public static void main(String[] args) {
        new RunEitherAsyncExample().runEitherAsyncExample();
    }

    private void runEitherAsyncExample() {
        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(this::generateNumber1);
        CompletableFuture<Integer> cf2 = CompletableFuture.supplyAsync(this::generateNumber2);
        CompletableFuture<Void> cfFirstConsumed = cf1.runEitherAsync(cf2, this::printNumber);
        System.out.printf("%s - Main\n", Thread.currentThread().getName());
        cfFirstConsumed.join();
        cf1.join();
        cf2.join();
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

    private void printNumber(Integer value) {
        System.out.printf("%s - Consumer - %s\n",
                Thread.currentThread().getName(), value);
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
