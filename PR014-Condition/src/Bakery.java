import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Bakery {

    private static final int TRAY_CAPACITY = 10;

    private final ArrayList<Integer> tray = new ArrayList<>();
    private final ReentrantLock lock = new ReentrantLock(true);
    private final Condition isNotFull = lock.newCondition();
    private final Condition isNotEmpty = lock.newCondition();

    public void addToTray(Integer doughnut) throws InterruptedException {
        lock.lock();
        try {
            while (tray.size() >= TRAY_CAPACITY) {
                System.out.println("Producer waiting for the tray to have room");
                isNotFull.await();
            }
            tray.add(doughnut);
            System.out.printf("Producer puts doughnut #%d on the tray\n", doughnut);
            isNotEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public Integer extractFromTray() throws InterruptedException {
        Integer doughnut;
        lock.lock();
        try {
            while (tray.isEmpty()) {
                System.out.println("Consumer waiting for the tray to have a doughnut");
                isNotEmpty.await();
            }
            doughnut = tray.remove(0);
            System.out.printf("Consumer extracts doughnut #%d from tray\n", doughnut);
            isNotFull.signal();
            return doughnut;
        } finally {
            lock.unlock();
        }
    }

}
