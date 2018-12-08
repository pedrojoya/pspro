import java.util.concurrent.SynchronousQueue;

public class Bakery {

    private final SynchronousQueue<Integer> tray = new SynchronousQueue<>();

    public void addToTray(Integer doughnut) throws InterruptedException {
        System.out.printf("Producer puts doughnut #%d on the tray\n", doughnut);
        tray.put(doughnut);
    }

    public Integer extractFromTray() throws InterruptedException {
        Integer doughnut = tray.take();
        System.out.printf("Consumer extracts doughnut #%d from tray\n", doughnut);
        return doughnut;
    }

}
