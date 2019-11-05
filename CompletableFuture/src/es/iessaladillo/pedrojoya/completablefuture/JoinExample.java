package es.iessaladillo.pedrojoya.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class JoinExample {

    public static void main(String[] args) {
        new JoinExample().joinExample();
    }

    private void joinExample() {
        CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(this::generateNumber);
        Integer value = cf.join();
        printNumber(value);
    }

    private int generateNumber() {
        System.out.printf("%s - Supplier\n", Thread.currentThread().getName());
        return 2;
    }

    private void printNumber(Integer value) {
        System.out.printf("%s - %d\n", Thread.currentThread().getName(), value);
    }

}
