package es.iessaladillo.pedrojoya.rejectedexecutionhandler;

import java.util.concurrent.*;

class Server {

    static class ServerThreadFactory implements ThreadFactory {

        int threadNumber = 1;

        @Override
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "Server thread " + threadNumber++);
        }

    }

    private final ThreadPoolExecutor fixedThreadPool =
            (ThreadPoolExecutor) Executors.newFixedThreadPool(5);

    Server() {
        fixedThreadPool.setThreadFactory(new ServerThreadFactory());
        fixedThreadPool.setRejectedExecutionHandler((runnable, executor) ->
                System.out.printf("Handler -> Task rejected: %s\n",
                        ((Task) runnable).getName()));
    }

    void execute(Task task) {
        fixedThreadPool.execute(task);
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
