public class Main {

    public static void main(String[] args) {
        Printer printer = new Printer();
        Thread[] printJobThreads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            printJobThreads[i] = new Thread(new PrintJob(printer, "Document #" + i), "Print job #" + i);
        }
        for (int i = 0; i < 10; i++) {
            printJobThreads[i].start();
        }
    }

}
