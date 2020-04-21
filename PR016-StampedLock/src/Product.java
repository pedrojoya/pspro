import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

public class Product {

    private double price;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private final StampedLock stampedLock = new StampedLock();

    public Product(double initialPrice) {
        this.price = initialPrice;
    }

    public double getPrice() throws InterruptedException {
        long stamp = stampedLock.tryOptimisticRead();
        return consultPrice(stamp);
    }

    private double consultPrice(long stamp) throws InterruptedException {
        System.out.printf("%s -> %s - Consulting price...\n",
                LocalTime.now().format(dateTimeFormatter),
                Thread.currentThread().getName());
        TimeUnit.SECONDS.sleep(3);
        double value = price;
        if (!stampedLock.validate(stamp)) {
            stamp = stampedLock.readLock();
            try {
                value = price;
            } finally {
                stampedLock.unlockRead(stamp);
            }
        }
        System.out.printf("%s -> %s - Price: %.2f\n",
                LocalTime.now().format(dateTimeFormatter),
                Thread.currentThread().getName(),
                value);
        return value;
    }

    public void updatePrice(double increment) throws InterruptedException {
        long stamp = stampedLock.writeLock();
        try {
            incrementPrice(increment);
        } finally {
            stampedLock.unlock(stamp);
        }
    }

    private void incrementPrice(double increment) throws InterruptedException {
        System.out.printf("%s -> %s - Updating price...\n",
                LocalTime.now().format(dateTimeFormatter),
                Thread.currentThread().getName());
        TimeUnit.SECONDS.sleep(1);
        this.price += increment;
        System.out.printf("%s -> %s - New price: %.2f\n",
                LocalTime.now().format(dateTimeFormatter),
                Thread.currentThread().getName(),
                this.price);
    }

}
