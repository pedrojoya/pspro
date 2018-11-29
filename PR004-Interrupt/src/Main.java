public class Main {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new PrimeNumberPrinter(), "Prime number printer");
        thread.start();
        // Sleep for 1 second.
        Thread.sleep(2000);
        // Active interruption flag in thread.
        thread.interrupt();
    }

}
