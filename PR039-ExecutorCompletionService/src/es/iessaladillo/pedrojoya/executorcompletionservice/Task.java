package es.iessaladillo.pedrojoya.executorcompletionservice;

import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

class Task implements Callable<FactorialCalculation> {

    private final String requester;
    private final int value;
    private final String when;

    Task(String requester, int value, String when) {
        this.requester = requester;
        this.value = value;
        this.when = when;
    }

    @Override
    public FactorialCalculation call() {
        Integer factorial;
        ResultOrThrowable<Integer> resultOrThrowable;
        try {
            factorial = factorial(value);
            resultOrThrowable = ResultOrThrowable.newResult(factorial);
        } catch (Exception e) {
            resultOrThrowable = ResultOrThrowable.newThrowable(e);
        }
        return new FactorialCalculation(requester, value, when, resultOrThrowable);
    }

    private Integer factorial(int number) throws InterruptedException {
        if (number < 0) throw new IllegalArgumentException("Number can't be negative");
        int factorial = 1;
        for (int i = 2; i <= number; i++) {
            factorial *= i;
        }
        TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(5) + 1);
        return factorial;
    }

}
