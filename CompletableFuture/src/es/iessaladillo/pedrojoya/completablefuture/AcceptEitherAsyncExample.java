package es.iessaladillo.pedrojoya.completablefuture;

import java.util.concurrent.CompletableFuture;

public class AcceptEitherAsyncExample {

    public static void main(String[] args) {
        new AcceptEitherAsyncExample().thenCombineAsyncExample();
    }

    private void thenCombineAsyncExample() {
        CompletableFuture<Integer> cf1 = CompletableFuture.supplyAsync(this::generateNumber);
        CompletableFuture<String> cf2 = CompletableFuture.supplyAsync(this::generateName);
        CompletableFuture<Void> cfCombined = cf1.thenCombineAsync(cf2, this::combineNumberAndName)
                .thenAcceptAsync(this::printCombination);

        System.out.printf("%s - Main\n", Thread.currentThread().getName());
        cfCombined.join();
    }

    private int generateNumber() {
        System.out.printf("%s - Number Supplier\n", Thread.currentThread().getName());
        return 2;
    }

    private String generateName() {
        System.out.printf("%s - Name Supplier\n", Thread.currentThread().getName());
        return "Baldomero";
    }

    private String combineNumberAndName(Integer number, String name) {
        String combination = name + " " + number;
        System.out.printf("%s - Bifunction: %s\n", Thread.currentThread().getName(), combination);
        return combination;
    }

    private void printCombination(String combination) {
        System.out.printf("%s - Consumer - %s\n",
                Thread.currentThread().getName(), combination);
    }

}
