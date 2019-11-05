package es.iessaladillo.pedrojoya.completablefuture;

import java.util.concurrent.CompletableFuture;

public class ThenApplyExample {

    public static void main(String[] args) {
        new ThenApplyExample().thenApplyExample();
    }

    private void thenApplyExample() {
        CompletableFuture.supplyAsync(() ->
                generateNumber()
        ).thenApply(value ->
                duplicate(value)
        ).thenAccept(value ->
                printNumber(value)
        );
        System.out.printf("%s - Main\n", Thread.currentThread().getName());
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

    private void printNumber(Integer generatedValue) {
        System.out.printf("%s - Consumer - Generated: %d\n",
                Thread.currentThread().getName(), generatedValue);
    }

}
