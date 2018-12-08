import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class DoughnutProducer implements Runnable {

    private final Bakery bakery;
    private int doughnutNumber;

    public DoughnutProducer(Bakery bakery) {
        Objects.requireNonNull(bakery);
        this.bakery = bakery;
    }

    @Override
    public void run() {
        Integer doughnut;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                doughnut = makeDoughnut();
            } catch (InterruptedException e) {
                System.out.println("Producer has been interrupted while making a doughnut");
                return;
            }
            try {
                if (doughnut % 3 == 0) {
                    bakery.addToTrayAndBlock(doughnut);
                } else {
                    bakery.addToTray(doughnut);
                }
            } catch (InterruptedException e) {
                System.out.println("Producer has been interrupted while adding a doughnut to the tray");
                return;
            }
        }
        System.out.println("Producer has been interrupted");
    }

    private int makeDoughnut() throws InterruptedException {
        int doughnut = ++doughnutNumber;
        System.out.printf("Producer is making doughnut #%d\n", doughnut);
        TimeUnit.SECONDS.sleep(1);
        return doughnut;
    }

}
