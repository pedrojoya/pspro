package es.iessaladillo.pedrojoya.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class SupplyAsyncExample {

    public static void main(String[] args) {
        new SupplyAsyncExample().suplyAsyncExample();
    }

    private void suplyAsyncExample() {
        CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(this::generateNumber);
        try {
            Integer value = cf.get();
            printNumber(value);
        } catch (InterruptedException ignored) {
        } catch (ExecutionException e) {
            System.out.println("Exception thrown in async task");
        }
    }

    private int generateNumber() {
        System.out.printf("%s - Supplier\n", Thread.currentThread().getName());
        return 2;
    }

    private void printNumber(Integer value) {
        System.out.printf("%s - %d\n", Thread.currentThread().getName(), value);
    }

}
