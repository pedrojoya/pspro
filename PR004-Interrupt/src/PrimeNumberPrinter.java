public class PrimeNumberPrinter implements Runnable {

    @Override
    public void run() {
        // Print prime numbers until someone interrupts the thread.
        for (long i = 1L; !Thread.currentThread().isInterrupted(); i++) {
            if (isPrimeNumber(i)) {
                System.out.printf("%d is a prime number\n", i);
                // If the thread is interrupted while sleeping
                // InterruptedException will be thrown.
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    System.out.println("I've been interrupted while sleeping");
                    return;
                }
            }
        }
        System.out.println("I've been interrupted");
    }

    private boolean isPrimeNumber(long number) {
        if (number < 1) {
            throw new IllegalArgumentException();
        }
        for (long i = 2; i * i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

}
