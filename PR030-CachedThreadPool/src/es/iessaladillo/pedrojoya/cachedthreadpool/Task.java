package es.iessaladillo.pedrojoya.cachedthreadpool;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

class Task implements Runnable {

    private final String name;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    Task(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.printf("%s -> %s -> Started at: %s\n",
                Thread.currentThread().getName(), name, dateTimeFormatter.format(LocalDateTime.now()));
        try {
            work();
        } catch (InterruptedException e) {
            System.out.printf("%s -> %s -> Interrupted at: %s\n",
                    Thread.currentThread().getName(), name, dateTimeFormatter.format(LocalDateTime.now()));
            return;
        }
        System.out.printf("%s -> %s -> Finished at: %s\n",
                Thread.currentThread().getName(), name, dateTimeFormatter.format(LocalDateTime.now()));
    }

    private void work() throws InterruptedException {
        TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
    }

}
