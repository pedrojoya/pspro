package es.iessaladillo.pedrojoya.executorcompletionservice;

import java.util.concurrent.*;

class Main {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        CompletionService<FactorialCalculation> completionService
                = new ExecutorCompletionService<>(executor);
        Thread taskProducer1 = new Thread(new TaskProducer("Task producer 1", completionService));
        Thread taskProducer2 = new Thread(new TaskProducer("Task producer 2", completionService));
        Thread taskConsumer = new Thread(new TaskConsumer(completionService));
        taskProducer1.start();
        taskProducer2.start();
        taskConsumer.start();
        try {
            TimeUnit.SECONDS.sleep(15);
            taskConsumer.interrupt();
            taskConsumer.join();
        } catch (InterruptedException ignored) {
        } finally {
            executor.shutdownNow();
        }
    }

}
