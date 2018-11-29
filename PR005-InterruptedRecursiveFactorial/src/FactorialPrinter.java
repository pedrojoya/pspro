public class FactorialPrinter implements Runnable {

    @Override
    public void run() {
        // Print factorials until it is interrupted
        for (int i = 1; !Thread.currentThread().isInterrupted(); i++) {
            try {
                System.out.printf("factorial(%d) = %d\n", i, factorial(i));
            } catch (InterruptedException e) {
                System.out.println("I've been interrupted");
                return;
            }
        }

    }

    private int factorial(int number) throws InterruptedException {
        if (number < 0) {
            throw new IllegalArgumentException();
        }
        Thread.sleep(50);
        if (number == 0 || number == 1) {
            return 1;
        } else {
            return number * factorial(number - 1);
        }
    }

}
