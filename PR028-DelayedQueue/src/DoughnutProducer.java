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
        Doughnut doughnut;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                doughnut = makeDoughnut();
            } catch (InterruptedException e) {
                System.out.println("Producer has been interrupted while making a doughnut");
                return;
            }
            bakery.addToTray(doughnut);
        }
        System.out.println("Producer has been interrupted");
    }

    private Doughnut makeDoughnut() throws InterruptedException {
        int doughnut = ++doughnutNumber;
        System.out.printf("Producer is making doughnut #%d\n", doughnut);
        TimeUnit.SECONDS.sleep(4);
        return new Doughnut(doughnut);
    }

}
