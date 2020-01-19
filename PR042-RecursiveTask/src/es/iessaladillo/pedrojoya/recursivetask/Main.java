package es.iessaladillo.pedrojoya.recursivetask;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadLocalRandom;

class Main {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        new Main().start();
    }

    private void start() throws InterruptedException, ExecutionException {
        int size = 1000;
        int[] values = createArray(size);
        FindMaxSecuentially(values);
        FindMaxInParallel(values);
    }

    private void FindMaxSecuentially(int[] values) {
        long start = System.currentTimeMillis();
        long sum = 0;
        for (int i = 0; i < values.length; i++) {
            sum += values[i];
        }
        System.out.printf("Secuential sum done in %d millis with result %d\n", System.currentTimeMillis() - start, sum);
    }

    private void FindMaxInParallel(int[] values) throws InterruptedException, ExecutionException {
        long start = System.currentTimeMillis();
        SumTask sumTask = new SumTask(values, 0, values.length);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        long max = forkJoinPool.invoke(sumTask);
        System.out.printf("Parallel sum done in %d millis with result %d\n", System.currentTimeMillis() - start, max);
        System.out.printf("Work steal count: %d\n", forkJoinPool.getStealCount());
        forkJoinPool.shutdown();
    }

    private int[] createArray(int size) {
        int[] values = new int[size];
        for (int i = 0; i < size; i++) {
            values[i] = ThreadLocalRandom.current().nextInt(100);
        }
        return values;
    }

}


