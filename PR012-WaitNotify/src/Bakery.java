import java.util.ArrayList;

public class Bakery {

    private static final int TRAY_CAPACITY = 10;

    private final ArrayList<Integer> tray = new ArrayList<>();

    public void addToTray(Integer doughnut) throws InterruptedException {
        synchronized (this) {
            while (tray.size() >= TRAY_CAPACITY) {
                System.out.println("Producer waiting for the tray to have room");
                wait();
            }
            tray.add(doughnut);
            System.out.printf("Producer puts doughnut #%d on the tray\n", doughnut);
            notifyAll();
        }
    }

    public Integer extractFromTray() throws InterruptedException {
        Integer doughnut;
        synchronized (this) {
            while (tray.isEmpty()) {
                System.out.println("Consumer waiting for the tray to have a doughnut");
                wait();
            }
            doughnut = tray.remove(0);
            System.out.printf("Consumer extracts doughnut #%d from tray\n", doughnut);
            notifyAll();
            return doughnut;
        }
    }

}
