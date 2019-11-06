package es.iessaladillo.pedrojoya.schedule;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.*;

class Main {

    public static void main(String[] args) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        ScheduledExecutorService scheduledExecutor =
                Executors.newSingleThreadScheduledExecutor();
        GreetTask greetTask = new GreetTask("Hello");
        ScheduledFuture<?> greetScheduledFuture = scheduledExecutor.schedule(greetTask, 5, TimeUnit.SECONDS);
        System.out.printf("%s -> %s - Greet task sent. Still %d seconds left\n",
                Thread.currentThread().getName(),
                dateTimeFormatter.format(LocalDateTime.now()),
                greetScheduledFuture.getDelay(TimeUnit.SECONDS));
        int number = 10;
        // Try with
        //int number = -1;
        int factorialTaskDelay = 10;
        // Try with
        // int factorialTaskDelay = 1;
        FactorialTask factorialTask = new FactorialTask(number);
        ScheduledFuture<Integer> factorialScheduledFuture =
                scheduledExecutor.schedule(factorialTask, factorialTaskDelay, TimeUnit.SECONDS);
        System.out.printf("%s -> %s - factorial(%d) task sent\n",
                Thread.currentThread().getName(),
                dateTimeFormatter.format(LocalDateTime.now()),
                number);
        System.out.printf("%s -> %s - Waiting for factorial(%d) result. Still %d seconds left\n",
                Thread.currentThread().getName(),
                dateTimeFormatter.format(LocalDateTime.now()),
                number,
                factorialScheduledFuture.getDelay(TimeUnit.SECONDS));
        try {
            Integer factorial = factorialScheduledFuture.get();
            System.out.printf("%s -> %s - factorial(%d) = %d\n",
                    Thread.currentThread().getName(),
                    dateTimeFormatter.format(LocalDateTime.now()),
                    number, factorial);
        } catch (InterruptedException ignored) {
        } catch (ExecutionException e) {
            System.out.printf("%s -> %s - factorial(%d) launched an exception\n",
                    Thread.currentThread().getName(),
                    dateTimeFormatter.format(LocalDateTime.now()),
                    number);
        } finally {
            scheduledExecutor.shutdown();
        }
    }

}
