package es.iessaladillo.pedrojoya.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class GetNowExample {

    public static void main(String[] args) {
        new GetNowExample().getNowExample();
    }

    private void getNowExample() {
        CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(this::generateNumber);
        Integer value = cf.getNow(0);
        printNumber(value);
        sleep(3000);
        Integer value2 = cf.getNow(0);
        printNumber(value2);
    }

    private void printNumber(Integer value) {
        System.out.printf("%s - Main - %d\n", Thread.currentThread().getName(), value);
    }

    private int generateNumber() {
        sleep(2000);
        System.out.printf("%s - Task\n", Thread.currentThread().getName());
        return 2;
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
