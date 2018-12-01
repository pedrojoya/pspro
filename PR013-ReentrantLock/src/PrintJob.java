import java.util.Objects;

public class PrintJob implements Runnable {

    private final Printer printer;
    private final String document;

    public PrintJob(Printer printer, String document) {
        Objects.requireNonNull(printer);
        Objects.requireNonNull(document);
        this.printer = printer;
        this.document = document;
    }

    @Override
    public void run() {
        try {
            printer.print(document);
        } catch (InterruptedException e) {
            System.out.printf("%s -> I've been interrupted while printing document\n",
                    Thread.currentThread().getName());
        }
    }

}
