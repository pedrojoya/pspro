package es.iessaladillo.pedrojoya.schedule;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

class GreetTask implements Runnable {

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    private final String greet;

    @SuppressWarnings("SameParameterValue")
    GreetTask(String greet) {
        this.greet = greet;
    }

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(6);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("%s -> %s - %s\n",
                Thread.currentThread().getName(),
                dateTimeFormatter.format(LocalDateTime.now()),
                greet);
    }

}
