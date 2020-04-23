package es.iessaladillo.pedrojoya.recursiveaction;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.RecursiveAction;

public class IncrementTask extends RecursiveAction {

    private final DateTimeFormatter dateTimeFormatter =
            DateTimeFormatter.ofPattern("HH:mm:ss:SSS");

    private final int[] values;
    private final int from;
    private final int to;
    private final int increment;

    IncrementTask(int[] values, int from, int to,
                  int increment) {
        this.values = values;
        this.from = from;
        this.to = to;
        this.increment = increment;
    }

    @Override
    protected void compute() {
        // If range is small enough update directly secuentially.
        if (to - from < 10) {
            incrementValues(values, from, to, increment);
        } else {
            applyStrategy1(values, from, to, increment);
//            applyStrategy2(values, from, to, increment);
        }
    }

    private void applyStrategy1(int[] values, int from, int to, int increment) {
        int pivot = (to + from) / 2;
        System.out.printf("%s - %s - [%d,%d) split in [%d,%d) y [%d,%d)\n",
                Thread.currentThread().getName(),
                dateTimeFormatter.format(LocalTime.now()),
                from, to, from, pivot, pivot, to);
        // Create two subtasks, one for each half.
        IncrementTask subTask1 = new IncrementTask(values, from, pivot, increment);
        IncrementTask subTask2 = new IncrementTask(values, pivot, to, increment);
        // Fork and join them.
        subTask1.fork();
        subTask2.fork();
        subTask1.join();
        subTask2.join();
        System.out.printf("%s - %s - [%d, %d) and [%d, %d) joined. [%d, %d] done\n",
                Thread.currentThread().getName(),
                dateTimeFormatter.format(LocalTime.now()),
                from, pivot, pivot, to, from, to);
    }

    @SuppressWarnings("unused")
    private void applyStrategy2(int[] values, int from, int to, int increment) {
        // Split range in two.
        int pivot = (to + from) / 2;
        System.out.printf("%s - %s - [%d,%d) split in [%d,%d) y [%d,%d)\n",
                Thread.currentThread().getName(),
                dateTimeFormatter.format(LocalTime.now()),
                from, to, from, pivot, pivot, to);
        IncrementTask subTask2 = new IncrementTask(values, pivot, to, increment);
        subTask2.fork();
        incrementValues(values, from, pivot, increment);
        subTask2.join();
        System.out.printf("%s - %s - [%d, %d) and [%d, %d) joined. [%d, %d] done\n",
                Thread.currentThread().getName(),
                dateTimeFormatter.format(LocalTime.now()),
                from, pivot, pivot, to, from, to);
    }

    private void incrementValues(int[] values, int from, int to, int increment) {
        for (int i = from; i < to; i++) {
            values[i] = values[i] * (1 + increment);
        }
        System.out.printf("%s - %s - [%d, %d) done secuentially\n",
                Thread.currentThread().getName(),
                dateTimeFormatter.format(LocalTime.now()),
                from, to);
    }

}