public class Main {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new FactorialPrinter(), "Factorial printer");
        thread.start();
        Thread.sleep(2000);
        thread.interrupt();
    }

}
