package es.iessaladillo.pedrojoya.invokeall;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

class Main {

    public static void main(String[] args) {
        ThreadPoolExecutor fixedThreadPool =
                (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        List<Task> tasks = new ArrayList<>();
        int[] numbers = new int[10];
        for (int i = 0; i < 10; i++) {
            numbers[i] = ThreadLocalRandom.current().nextInt(15) - 5;
            tasks.add(new Task(numbers[i]));
        }
        try {
            List<Future<Integer>> futureList = fixedThreadPool.invokeAll(tasks, 2, TimeUnit.SECONDS);
            System.out.print("Results:\n");
            for (int i = 0; i < futureList.size(); i++) {
                // This get returns inmediately.
                Future<Integer> future = futureList.get(i);
                try {
                    Integer factorial = future.get();
                    System.out.printf("Task %d -> factorial(%d) = %d\n", i + 1,
                            numbers[i], factorial);
                } catch (ExecutionException e) {
                    System.out.printf("Task %d -> factorial(%d) threw an exception\n",
                            i + 1, numbers[i]);
                } catch (CancellationException e) {
                    System.out.printf("Task %d -> factorial(%d) was cancelled\n",
                            i + 1, numbers[i]);
                }
            }
        } catch (InterruptedException ignored) {
        } finally {
            fixedThreadPool.shutdown();
        }
    }

}
