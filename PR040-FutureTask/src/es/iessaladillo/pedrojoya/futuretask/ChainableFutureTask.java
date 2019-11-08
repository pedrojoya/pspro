package es.iessaladillo.pedrojoya.futuretask;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.FutureTask;

public class ChainableFutureTask<V> extends FutureTask<V> {

    private Callable<V> next;

    public ChainableFutureTask(Callable<V> callable) {
        super(callable);
    }

    private ChainableFutureTask(V previousValue, Callable<V> callable) {
        super(callable);
    }

    @Override
    protected void done() {
        if (next != null) {
            try {
                new ChainableFutureTask<V>(get(), next);
            } catch (InterruptedException | ExecutionException ignored) {
            }
        }
    }

    public ChainableFutureTask<V> then(Callable<V> nextOperation) {
        this.next = nextOperation;
        return this;
    }

}
