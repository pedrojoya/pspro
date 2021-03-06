package es.iessaladillo.pedrojoya.cachedthreadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

class Server {

    private final ThreadPoolExecutor cachedThreadPool =
            (ThreadPoolExecutor) Executors.newCachedThreadPool();

    void execute(Task task) {
        try {
            cachedThreadPool.execute(task);
            System.out.printf("Server -> Thread pool size: %d\n", cachedThreadPool.getPoolSize());
            System.out.printf("Server -> Active threads count: %d\n", cachedThreadPool.getActiveCount());
        } catch (Exception e) {
            System.out.printf("Server -> Task rejected: %s\n", task.getName());
        }
    }

    void shutdown() throws InterruptedException {
        cachedThreadPool.shutdown();
        if (cachedThreadPool.awaitTermination(5, TimeUnit.SECONDS)) {
            System.out.printf("Server -> Terminated. Completed: %d\n",
                    cachedThreadPool.getCompletedTaskCount());
        } else {
            System.out.printf("Server -> Await termination timeout. Completed: %d\n",
                    cachedThreadPool.getCompletedTaskCount());
        }
    }

    @SuppressWarnings("unused")
    void shutdownNow() throws InterruptedException {
        cachedThreadPool.shutdownNow();
        if (cachedThreadPool.awaitTermination(5, TimeUnit.SECONDS)) {
            System.out.printf("Server -> Terminated. Completed: %d\n",
                    cachedThreadPool.getCompletedTaskCount());
        } else {
            System.out.printf("Server -> Await termination timeout. Completed: %d\n",
                    cachedThreadPool.getCompletedTaskCount());
        }
    }

}
