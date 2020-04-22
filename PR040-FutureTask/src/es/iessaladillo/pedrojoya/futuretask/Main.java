package es.iessaladillo.pedrojoya.futuretask;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        final DateTimeFormatter dateTimeFormatter =
                DateTimeFormatter.ofPattern("HH:mm:ss");
        ExecutorService executor = Executors.newCachedThreadPool();
        LogFutureTask<Integer> task1 = new LogFutureTask<>(new FactorialTask(-1));
        LogFutureTask<Integer> task2 = new LogFutureTask<>(new FactorialTask(4));
        Thread thread = new Thread(task1);
        System.out.printf("%s - %s - Task1 started\n",
                Thread.currentThread().getName(),
                dateTimeFormatter.format(LocalTime.now()));
        thread.start();
        System.out.printf("%s - %s - Task2 sent\n",
                Thread.currentThread().getName(),
                dateTimeFormatter.format(LocalTime.now()));
        executor.submit(task2);
        executor.shutdown();
        TimeUnit.SECONDS.sleep(2);
        //task1.cancel(true);
        //task2.cancel(true);
        thread.join();
        executor.awaitTermination(1, TimeUnit.MINUTES);
    }

}
