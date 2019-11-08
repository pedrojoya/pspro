package es.iessaladillo.pedrojoya.futuretask;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class LogFutureTask<V> extends FutureTask<V> {

    private final DateTimeFormatter dateTimeFormatter =
            DateTimeFormatter.ofPattern("HH:mm:ss");

    LogFutureTask(Callable<V> callable) {
        super(callable);
    }

    public LogFutureTask(Runnable runnable, V result) {
        super(runnable, result);
    }

    @Override
    protected void done() {
        if (isCancelled()) {
            System.out.printf("%s - %s - Operation cancelled\n",
                    Thread.currentThread().getName(),
                    dateTimeFormatter.format(LocalDateTime.now()));
        } else {
            try {
                V value = get();
                System.out.printf("%s - %s - Result: %s\n",
                        Thread.currentThread().getName(),
                        dateTimeFormatter.format(LocalDateTime.now()),
                        value);
            } catch (InterruptedException ignored) {
            } catch (ExecutionException e) {
                System.out.printf("%s - %s - %s\n",
                        Thread.currentThread().getName(),
                        dateTimeFormatter.format(LocalDateTime.now()),
                        e.getClass().getSimpleName());
            }
        }
    }

}
