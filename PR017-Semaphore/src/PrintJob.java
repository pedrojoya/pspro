import java.util.Objects;

public class PrintJob implements Runnable {

    private final PrintingQueue printingQueue;
    private final String document;

    public PrintJob(PrintingQueue printingQueue, String document) {
        Objects.requireNonNull(printingQueue);
        Objects.requireNonNull(document);
        this.printingQueue = printingQueue;
        this.document = document;
    }

    @Override
    public void run() {
        try {
            printingQueue.addDocument(document);
        } catch (InterruptedException e) {
            System.out.printf("%s -> I've been interrupted while printing document\n",
                    Thread.currentThread().getName());
        }
    }

}
