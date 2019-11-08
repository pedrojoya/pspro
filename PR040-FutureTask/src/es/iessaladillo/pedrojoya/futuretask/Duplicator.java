package es.iessaladillo.pedrojoya.futuretask;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;

public class Duplicator extends FutureTask<Integer> {

    private final ExecutorService executor;

    Duplicator(Callable<Integer> callable, ExecutorService executor) {
        super(callable);
        this.executor = executor;
    }

    @Override
    protected void done() {
        try {
            executor.submit(new DuplicationTask(get()));
        } catch (InterruptedException ignored) {
        } catch (ExecutionException e) {
        }
    }
}
