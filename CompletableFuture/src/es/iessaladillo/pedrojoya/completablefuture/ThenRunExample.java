package es.iessaladillo.pedrojoya.completablefuture;

import java.util.concurrent.CompletableFuture;

public class ThenRunExample {

    public static void main(String[] args) {
        new ThenRunExample().runAsyncExample();
    }

    private void runAsyncExample() {
        CompletableFuture.runAsync(() -> printInfo());
        System.out.printf("%s - Main\n", Thread.currentThread().getName());
    }

    private void printInfo() {
        System.out.printf("%s - Task\n", Thread.currentThread().getName());
    }

}
