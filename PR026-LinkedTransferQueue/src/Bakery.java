import java.util.concurrent.LinkedTransferQueue;

public class Bakery {

    private final LinkedTransferQueue<Integer> tray = new LinkedTransferQueue<>();

    public void addToTray(Integer doughnut) {
        System.out.printf("Producer puts doughnut #%d on the tray\n", doughnut);
        tray.put(doughnut);
    }

    public void addToTrayAndBlock(Integer doughnut) throws InterruptedException {
        System.out.printf("Producer puts doughnut #%d on the tray and waits to the consumer to extract it\n", doughnut);
        tray.transfer(doughnut);
    }

    public Integer extractFromTray() throws InterruptedException {
        Integer doughnut = tray.take();
        System.out.printf("Consumer extracts doughnut #%d from tray\n", doughnut);
        return doughnut;
    }

}
