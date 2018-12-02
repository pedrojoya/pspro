import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintingQueue {

    private static final int NO_PRINTER = -1;

    private final Semaphore semaphore;
    private final Lock reentrantLock = new ReentrantLock(true);
    private final Printer[] printers;
    private final boolean[] printerAvailable;

    public PrintingQueue(int numberOfPrinters) {
        semaphore = new Semaphore(numberOfPrinters, true);
        printers = new Printer[numberOfPrinters];
        printerAvailable = new boolean[numberOfPrinters];
        for (int i = 0; i < numberOfPrinters; i++) {
            printers[i] = new Printer(i);
            printerAvailable[i] = true;
        }
    }

    public void addDocument(String document) throws InterruptedException {
        try {
            semaphore.acquire();
            int printerNumber = selectPrinter();
            if (printerNumber != NO_PRINTER) {
                printers[printerNumber].printDocument(document);
            }
            printerAvailable[printerNumber] = true;
        } finally {
            // This is called even if an exception is thrown.
            semaphore.release();
        }
    }

    private int selectPrinter() {
        reentrantLock.lock();
        try {
            for (int i = 0; i < printers.length; i++) {
                if (printerAvailable[i]) {
                    printerAvailable[i] = false;
                    return i;
                }
            }
        } finally {
            reentrantLock.unlock();
        }
        return NO_PRINTER;
    }

}
