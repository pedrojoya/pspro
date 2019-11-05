package es.iessaladillo.pedrojoya.completablefuture;

import java.util.concurrent.CompletableFuture;

public class ThenRunAsyncExample {

    public static void main(String[] args) {
        new ThenRunAsyncExample().thenRunExample();
    }

    private void thenRunExample() {
        CompletableFuture.runAsync(() ->
            printInfo()
        ).thenRun(() ->
            printMoreInfo()
        );
        System.out.printf("%s - Main\n", Thread.currentThread().getName());
    }

    private void printInfo() {
        System.out.printf("%s - Task1\n", Thread.currentThread().getName());
    }

    private void printMoreInfo() {
        System.out.printf("%s - Task2\n", Thread.currentThread().getName());
    }

}
