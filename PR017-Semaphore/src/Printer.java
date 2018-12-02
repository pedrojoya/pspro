import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Printer {

    private final int printerNumber;
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
    private final Random random = new Random();

    public Printer(int printerNumber) {
        this.printerNumber = printerNumber;
    }

    public void printDocument(String document) throws InterruptedException {
        System.out.printf("%s -> %s: Document printing started on printer %d\n",
                simpleDateFormat.format(new Date()), Thread.currentThread().getName(), printerNumber);
        System.out.printf("%s -> %s: %s...\n",
                simpleDateFormat.format(new Date()), Thread.currentThread().getName(), document);
        TimeUnit.SECONDS.sleep(random.nextInt(5));
        System.out.printf("%s -> %s: Printing finished on printer %d\n",
                simpleDateFormat.format(new Date()), Thread.currentThread().getName(), printerNumber);
    }

}
