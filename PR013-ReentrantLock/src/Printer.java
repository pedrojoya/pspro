import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Printer {

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
    private final Random random = new Random();
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
                simpleDateFormat.format(new Date()), Thread.currentThread().getName());
        System.out.printf("%s -> %s: %s...\n",
                simpleDateFormat.format(new Date()), Thread.currentThread().getName(), document);
        TimeUnit.SECONDS.sleep(random.nextInt(5));
        System.out.printf("%s -> %s: Printing finished\n",
                simpleDateFormat.format(new Date()), Thread.currentThread().getName());
    }

}
