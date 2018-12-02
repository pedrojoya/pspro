import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Product {

    private double price;
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
    private final ReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = reentrantReadWriteLock.readLock();
    private final Lock writeLock = reentrantReadWriteLock.writeLock();

    public Product(double initialPrice) {
        this.price = initialPrice;
    }

    public double getPrice() throws InterruptedException {
        readLock.lock();
        try {
            return consultPrice();
        } finally {
            readLock.unlock();
        }
    }

    private double consultPrice() throws InterruptedException {
        System.out.printf("%s -> %s - Consulting price...\n",
                simpleDateFormat.format(new Date()),
                Thread.currentThread().getName());
        TimeUnit.SECONDS.sleep(3);
        System.out.printf("%s -> %s - Price: %.2f\n",
                simpleDateFormat.format(new Date()),
                Thread.currentThread().getName(),
                price);
        return price;
    }

    public void updatePrice(double increment) throws InterruptedException {
        writeLock.lock();
        try {
            incrementPrice(increment);
        } finally {
            writeLock.unlock();
        }
    }

    private void incrementPrice(double increment) throws InterruptedException {
        System.out.printf("%s -> %s - Updating price...\n",
                simpleDateFormat.format(new Date()),
                Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(1);
        this.price += increment;
        System.out.printf("%s -> %s - New price: %.2f\n",
                simpleDateFormat.format(new Date()),
                Thread.currentThread().getName(),
                this.price);
    }

}
