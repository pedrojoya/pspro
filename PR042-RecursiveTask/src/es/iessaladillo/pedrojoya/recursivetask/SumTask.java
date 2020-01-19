package es.iessaladillo.pedrojoya.recursivetask;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.RecursiveTask;

public class SumTask extends RecursiveTask<Long> {

    private final DateTimeFormatter dateTimeFormatter =
            DateTimeFormatter.ofPattern("HH:mm:ss:SSS");

    private int[] values;
    private int from;
    private int to;

    SumTask(int[] values, int from, int to) {
        this.values = values;
        this.from = from;
        this.to = to;
    }

    @Override
    protected Long compute() {
        // If range is small enough update directly secuentially.
        if (to - from < 10) {
            return sum(values, from, to);
        } else {
             long sum = applyStrategy1(values, from, to);
//            long sum = applyStrategy2(values, from, to);
            return sum;
        }
    }

    private long applyStrategy1(int[] values, int from, int to) {
        int pivot = (to + from) / 2;
        System.out.printf("%s - %s - [%d,%d) split in [%d,%d) y [%d,%d)\n",
                Thread.currentThread().getName(),
                dateTimeFormatter.format(LocalDateTime.now()),
                from, to, from, pivot, pivot, to);
        SumTask subTask1 = new SumTask(values, from, pivot);
        SumTask subTask2 = new SumTask(values, pivot, to);
        subTask1.fork();
        subTask2.fork();
        long sum1 = subTask1.join();
        long sum2 = subTask2.join();
        long sum = sum1 + sum2;
        System.out.printf("%s - %s - [%d, %d) joined with %d\n",
                Thread.currentThread().getName(),
                dateTimeFormatter.format(LocalDateTime.now()),
                from, pivot, sum1);
        System.out.printf("%s - %s - [%d, %d) joined with %d\n",
                Thread.currentThread().getName(),
                dateTimeFormatter.format(LocalDateTime.now()),
                pivot, to, sum2);
        System.out.printf("%s - %s - [%d, %d) done with %d\n",
                Thread.currentThread().getName(),
                dateTimeFormatter.format(LocalDateTime.now()),
                from, to, sum);
        return sum;
    }

    private long applyStrategy2(int[] values, int from, int to) {
        int pivot = (to + from) / 2;
        System.out.printf("%s - %s - [%d,%d) split in [%d,%d) y [%d,%d)\n",
                Thread.currentThread().getName(),
                dateTimeFormatter.format(LocalDateTime.now()),
                from, to, from, pivot, pivot, to);
        SumTask subTask2 = new SumTask(values, pivot, to);
        subTask2.fork();
        long sum1 = sum(values, from, pivot);
        long sum2 = subTask2.join();
        long sum = sum1 + sum2;
        System.out.printf("%s - %s - [%d, %d) joined with %d\n",
                Thread.currentThread().getName(),
                dateTimeFormatter.format(LocalDateTime.now()),
                pivot, to, sum2);
        System.out.printf("%s - %s - [%d, %d) done with %d\n",
                Thread.currentThread().getName(),
                dateTimeFormatter.format(LocalDateTime.now()),
                from, to, sum);
        return sum;
    }

    private long sum(int[] values, int from, int to) {
        long sum = 0;
        for (int i = from; i < to; i++) {
            sum += values[i];
        }
        System.out.printf("%s - %s - [%d, %d] done secuentially with %d\n",
                Thread.currentThread().getName(),
                dateTimeFormatter.format(LocalDateTime.now()),
                from, to, sum);
        return sum;
    }

}