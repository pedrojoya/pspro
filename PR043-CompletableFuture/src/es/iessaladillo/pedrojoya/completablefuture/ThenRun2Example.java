package es.iessaladillo.pedrojoya.completablefuture;

import java.util.concurrent.CompletableFuture;

public class ThenRun2Example {

    public static void main(String[] args) {
        new ThenRun2Example().thenRun2Example();
    }

    private void thenRun2Example() {
        CompletableFuture<Void> cf =
                CompletableFuture.runAsync(this::printInfo);
        sleep(1000);
        cf.thenRun(this::printMoreInfo);
        System.out.printf("%s - Main\n", Thread.currentThread().getName());
    }

    private void printInfo() {
        System.out.printf("%s - Task1\n", Thread.currentThread().getName());
    }

    private void printMoreInfo() {
        System.out.printf("%s - Task2\n", Thread.currentThread().getName());
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
