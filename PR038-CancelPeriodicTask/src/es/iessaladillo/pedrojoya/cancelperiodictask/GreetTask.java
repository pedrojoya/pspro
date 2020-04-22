package es.iessaladillo.pedrojoya.cancelperiodictask;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

class GreetTask implements Runnable {

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private final String greet;

    @SuppressWarnings("SameParameterValue")
    GreetTask(String greet) {
        this.greet = greet;
    }

    @Override
    public void run() {
        System.out.printf("%s -> %s - %s\n",
                Thread.currentThread().getName(),
                dateTimeFormatter.format(LocalTime.now()),
                greet);
    }

}
