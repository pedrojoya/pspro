package es.iessaladillo.pedrojoya.invokeall;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

class Task implements Callable<Integer> {

    private final int number;

    Task(int number) {
        this.number = number;
    }

    @Override
    public Integer call() throws InterruptedException {
        return factorial(number);
    }

    private Integer factorial(int number) throws InterruptedException {
        if (number < 0) throw new IllegalArgumentException("Number can't be negative");
        int factorial = 1;
        for (int i = 2; i <= number; i++) {
            factorial *= i;
            TimeUnit.MILLISECONDS.sleep(200);
        }
        return factorial;
    }

}
