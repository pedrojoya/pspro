package es.iessaladillo.pedrojoya.recursiveaction;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

class Main {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        new Main().start();
    }

    private void start() throws InterruptedException, ExecutionException {
        int size = 1000;
        int increment = 5;
        int[] values = createArray(size);
        incrementSecuentially(values, increment);
        incrementInParallel(values, increment);
    }

    private void incrementSecuentially(int[] values, int increment) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < values.length; i++) {
            values[i] = values[i] + increment;
        }
        System.out.printf("Secuential increment done in %d millis\n", System.currentTimeMillis() - start);
    }

    private void incrementInParallel(int[] values, int increment) throws InterruptedException, ExecutionException {
        long start = System.currentTimeMillis();
        IncrementTask incrementTask = new IncrementTask(values, 0, values.length, increment);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.execute(incrementTask);
        incrementTask.get();
        System.out.printf("Parallel increment done in %d millis\n", System.currentTimeMillis() - start);
        System.out.printf("Work steal count: %d\n", forkJoinPool.getStealCount());
        forkJoinPool.shutdown();
    }

    private int[] createArray(int size) {
        int[] values = new int[size];
        for (int i = 0; i < size; i++) {
            values[i] = 10;
        }
        return values;
    }

}


