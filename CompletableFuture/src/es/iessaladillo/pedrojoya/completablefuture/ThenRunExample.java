package es.iessaladillo.pedrojoya.completablefuture;

import java.util.concurrent.CompletableFuture;

public class ThenRunExample {

    public static void main(String[] args) {
        new ThenRunExample().thenRunExample();
    }

    private void thenRunExample() {
        CompletableFuture<Void> cf = CompletableFuture.runAsync(this::printInfo)
                .thenRun(this::printMoreInfo);
        System.out.printf("%s - Main\n", Thread.currentThread().getName());
        cf.join();
    }

    private void printInfo() {
        System.out.printf("%s - Task1\n", Thread.currentThread().getName());
    }

    private void printMoreInfo() {
        System.out.printf("%s - Task2\n", Thread.currentThread().getName());
    }

}
