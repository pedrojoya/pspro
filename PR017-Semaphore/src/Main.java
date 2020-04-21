public class Main {

    public static void main(String[] args) {
        PrintingQueue printingQueue = new PrintingQueue(3);
        Thread[] printJobThreads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            printJobThreads[i] = new Thread(new PrintJob(printingQueue, "Document #" + i), "Print job #" + i);
        }
        for (int i = 0; i < 10; i++) {
            printJobThreads[i].start();
        }
    }

}
