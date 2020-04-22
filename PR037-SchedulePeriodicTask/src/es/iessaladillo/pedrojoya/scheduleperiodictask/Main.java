package es.iessaladillo.pedrojoya.scheduleperiodictask;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class Main {

    public static void main(String[] args) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        ScheduledThreadPoolExecutor scheduledExecutor =
                (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1);
        GreetTask greetTask = new GreetTask("Hello");
        int periodSeconds = 5;
        ScheduledFuture<?> greetScheduledFuture =
                scheduledExecutor.scheduleAtFixedRate(greetTask, 5, periodSeconds, TimeUnit.SECONDS);
        System.out.printf("%s -> %s - Greet task sent to be executed every %d seconds. Still %d seconds left to first ejecution\n",
                Thread.currentThread().getName(),
                dateTimeFormatter.format(LocalTime.now()),
                periodSeconds,
                greetScheduledFuture.getDelay(TimeUnit.SECONDS));
        int sleepSeconds = 25;
        try {
            TimeUnit.SECONDS.sleep(sleepSeconds);
        } catch (InterruptedException ignored) {
        } finally {
            System.out.printf("%s -> %s - Executor shut down after %d seconds\n",
                    Thread.currentThread().getName(),
                    dateTimeFormatter.format(LocalTime.now()),
                    sleepSeconds);
            scheduledExecutor.shutdownNow();
        }
    }

}
