package es.iessaladillo.pedrojoya.executorcompletionservice;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

class TaskProducer implements Runnable {

    private final DateTimeFormatter dateTimeFormatter =
            DateTimeFormatter.ofPattern("HH:mm:ss");
    private final String name;
    private final CompletionService<FactorialCalculation> completionService;

    TaskProducer(String name, CompletionService<FactorialCalculation> completionService) {
        this.name = name;
        this.completionService = completionService;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            int value = ThreadLocalRandom.current().nextInt(15) - 5;
            String when = dateTimeFormatter.format(LocalDateTime.now());
            System.out.printf("%s -> %s requests factorial(%d) at %s\n",
                    Thread.currentThread().getName(), name, value, when);
            completionService.submit(new Task(name, value, when));
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.out.printf("%s -> %s interrupted\n",
                        Thread.currentThread().getName(), name);
                return;
            }
        }
        System.out.printf("%s -> %s finished\n",
                Thread.currentThread().getName(), name);
    }

}
