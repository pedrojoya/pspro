package es.iessaladillo.pedrojoya.executorcompletionservice;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

class TaskConsumer implements Runnable {

    private final CompletionService<FactorialCalculation> completionService;

    TaskConsumer(CompletionService<FactorialCalculation> completionService) {
        this.completionService = completionService;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            Future<FactorialCalculation> taskResultFuture;
            try {
                taskResultFuture = completionService.take();
                if (taskResultFuture != null) {
                    FactorialCalculation factorialCalculation = taskResultFuture.get();
                    if (factorialCalculation.getThrowable() == null) {
                        showResult(factorialCalculation);
                    } else {
                        showError(factorialCalculation);
                    }
                }
            } catch (InterruptedException e) {
                System.out.printf("%s -> Task consumer interrupted\n",
                        Thread.currentThread().getName());
                return;
            } catch (ExecutionException e) {
                System.out.printf("%s -> Task consumer calculation poll - Error calculating factorial\n",
                        Thread.currentThread().getName());
            }
        }
        System.out.printf("%s -> Task consumer finished\n",
                Thread.currentThread().getName());
    }

    private void showError(FactorialCalculation factorialCalculation) {
        System.out.printf("%s -> Task consumer calculation poll - factorial(%d) threw %s, requested by %s at %s\n",
                Thread.currentThread().getName(), factorialCalculation.getValue(),
                factorialCalculation.getThrowable().getClass().getSimpleName(), factorialCalculation.getRequester(),
                factorialCalculation.getWhen());
    }

    private void showResult(FactorialCalculation factorialCalculation) {
        System.out.printf("%s -> Task consumer calculation poll - factorial(%d) = %d requested by %s at %s\n",
                Thread.currentThread().getName(), factorialCalculation.getValue(),
                factorialCalculation.getResult(), factorialCalculation.getRequester(),
                factorialCalculation.getWhen());
    }

}
