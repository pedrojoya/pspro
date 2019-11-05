package es.iessaladillo.pedrojoya.callable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

class Main {

    public static void main(String[] args) {
        ThreadPoolExecutor fixedThreadPool =
                (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        List<Future<Integer>> futureList = new ArrayList<>();
        int[] numbers = new int[10];
        for (int i = 0; i < 10; i++) {
            numbers[i] = ThreadLocalRandom.current().nextInt(15) - 5;
            Task task = new Task(numbers[i]);
            futureList.add(fixedThreadPool.submit(task));
        }
        // Main thread can do some work here.
        // ...
        System.out.print("Results:\n");
        try {
            for (int i = 0; i < futureList.size(); i++) {
                Future<Integer> future = futureList.get(i);
                try {
                    Integer factorial = future.get();
                    System.out.printf("Task %d -> factorial(%d) = %d\n", i + 1,
                            numbers[i], factorial);
                } catch (ExecutionException e) {
                    System.out.printf("Task %d -> factorial(%d) threw an exception\n",
                            i + 1, numbers[i]);
                }
            }
        } catch (InterruptedException ignored) {
        } finally {
            fixedThreadPool.shutdown();
        }
    }

}
