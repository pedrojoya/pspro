import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class DoughnutConsumer implements Runnable {

    private final Bakery bakery;

    public DoughnutConsumer(Bakery bakery) {
        Objects.requireNonNull(bakery);
        this.bakery = bakery;
    }

    @Override
    public void run() {
        Integer doughnut;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                doughnut = bakery.extractFromTray();
            } catch (InterruptedException e) {
                System.out.println("Consumer has been interrupted while extracting from tray");
                return;
            }
            try {
                eat(doughnut);
            } catch (InterruptedException e) {
                System.out.println("Consumer has been interrupted while eating");
                return;
            }
        }
        System.out.println("Consumer has been interrupted");
    }

    private void eat(int doughnut) throws InterruptedException {
        System.out.printf("Consumer is eating doughnut #%d\n", doughnut);
        TimeUnit.SECONDS.sleep(1);
    }

}
