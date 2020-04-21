import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Printer {

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private final Lock reentrantLock = new ReentrantLock(true);

    public void print(String document) throws InterruptedException {
        reentrantLock.lock();
        try {
            printDocument(document);
        } finally {
            // This is called even if an exception is thrown.
            reentrantLock.unlock();
        }
    }

    private void printDocument(String document) throws InterruptedException {
        System.out.printf("%s -> %s: Document printing started\n",
                LocalTime.now().format(dateTimeFormatter), Thread.currentThread().getName());
        System.out.printf("%s -> %s: %s...\n",
                LocalTime.now().format(dateTimeFormatter), Thread.currentThread().getName(), document);
        TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(5));
        System.out.printf("%s -> %s: Printing finished\n",
                LocalTime.now().format(dateTimeFormatter), Thread.currentThread().getName());
    }

}
