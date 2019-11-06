package es.iessaladillo.pedrojoya.schedule;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

class FactorialTask implements Callable<Integer> {

    private final int number;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    FactorialTask(int number) {
        this.number = number;
    }

    @Override
    public Integer call() throws InterruptedException {
        Integer result = factorial(number);
        System.out.printf("%s -> %s - factorial(%d) calculated\n",
                Thread.currentThread().getName(),
                dateTimeFormatter.format(LocalDateTime.now()),
                number);
        return result;
    }

    private Integer factorial(int number) throws InterruptedException {
        if (number < 0) throw new IllegalArgumentException("Number can't be negative");
        int factorial = 1;
        for (int i = 2; i <= number; i++) {
            factorial *= i;
            TimeUnit.MILLISECONDS.sleep(20);
        }
        return factorial;
    }

}
