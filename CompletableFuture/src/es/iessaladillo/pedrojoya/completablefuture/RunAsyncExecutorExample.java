package es.iessaladillo.pedrojoya.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

public class RunAsyncExecutorExample {

    public static void main(String[] args) {
        new RunAsyncExecutorExample().runAsyncExecutorExample();
    }

    private void runAsyncExecutorExample() {
        CompletableFuture.runAsync(this::printInfo, Executors.newFixedThreadPool(1));
        System.out.printf("%s - Main\n", Thread.currentThread().getName());
    }

    private void printInfo() {
        System.out.printf("%s - Task\n", Thread.currentThread().getName());
    }

}
