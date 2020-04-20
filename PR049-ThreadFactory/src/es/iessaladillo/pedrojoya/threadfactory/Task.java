package es.iessaladillo.pedrojoya.threadfactory;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Task implements Runnable {

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Override
    public void run() {
        try {
            System.out.printf("%s -> %s started\n",
                    LocalTime.now().format(dateTimeFormatter), Thread.currentThread().getName());
            // Simulate task working.
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(5) + 1);
            System.out.printf("%s -> %s finished\n",
                    LocalTime.now().format(dateTimeFormatter), Thread.currentThread().getName());
        } catch (InterruptedException ignored) {
        }
    }

}
