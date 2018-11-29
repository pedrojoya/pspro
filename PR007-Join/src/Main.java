public class Main {

    private static final int LAST_NUMBER = 10;

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[LAST_NUMBER + 1];
        for (int i = 1; i <= LAST_NUMBER; i++) {
            threads[i-1] = new Thread(new MultiplicationTable(i), "Table of " + i);
            threads[i-1].start();
        }
        for (int i = 1; i <= LAST_NUMBER; i++) {
            threads[i-1].join();
        }
        System.out.println("All multiplication tables printed");
    }

}
