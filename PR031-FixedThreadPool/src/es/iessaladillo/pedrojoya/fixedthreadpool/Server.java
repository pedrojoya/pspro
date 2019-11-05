package es.iessaladillo.pedrojoya.fixedthreadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class Server {

    private final ThreadPoolExecutor fixedThreadPool =
            (ThreadPoolExecutor) Executors.newFixedThreadPool(5);

    void execute(Task task) {
        try {
            fixedThreadPool.execute(task);
            System.out.printf("Server -> Thread pool size: %d\n", fixedThreadPool.getPoolSize());
            System.out.printf("Server -> Active threads count: %d\n", fixedThreadPool.getActiveCount());
        } catch (RejectedExecutionException e) {
            System.out.printf("Server -> Task rejected: %s\n", task.getName());
        }
    }

    void shutdown() throws InterruptedException {
        fixedThreadPool.shutdown();
        if (fixedThreadPool.awaitTermination(5, TimeUnit.SECONDS)) {
            System.out.printf("Server -> Terminated. Completed: %d\n",
                    fixedThreadPool.getCompletedTaskCount());
        } else {
            System.out.printf("Server -> Await termination timeout. Completed: %d\n",
                    fixedThreadPool.getCompletedTaskCount());
        }
    }

    void shutdownNow() throws InterruptedException {
        fixedThreadPool.shutdownNow();
        if (fixedThreadPool.awaitTermination(5, TimeUnit.SECONDS)) {
            System.out.printf("Server -> Terminated. Completed: %d\n",
                    fixedThreadPool.getCompletedTaskCount());
        } else {
            System.out.printf("Server -> Await termination timeout. Completed: %d\n",
                    fixedThreadPool.getCompletedTaskCount());
        }
    }

}
