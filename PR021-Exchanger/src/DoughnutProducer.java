import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class DoughnutProducer implements Runnable {

    private final Exchanger<List<Integer>> exchanger;
    private List<Integer> buffer = new ArrayList<>();
    private int doughnutNumber;

    public DoughnutProducer(Exchanger<List<Integer>> exchanger) {
        Objects.requireNonNull(exchanger);
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        Integer doughnut;
        while (!Thread.currentThread().isInterrupted()) {
            // Fill the buffer and then exchange.
            for (int i = 0; i < Main.BUFFER_SIZE; i++){
                try {
                    doughnut = makeDoughnut();
                } catch (InterruptedException e) {
                    System.out.println("Producer has been interrupted while making a doughnut");
                    return;
                }
                System.out.printf("Producer has made doughnut #%d\n", doughnut);
                buffer.add(doughnut);
            }
            System.out.println("Producer ready for exchange");
            try {
                buffer = exchanger.exchange(buffer, 20, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                System.out.println("Producer has been interrupted while exchanging buffer");
                return;
            } catch (TimeoutException e) {
                System.out.println("Producer can't wait for the consumer anymore");
                return;
            }
        }
        System.out.println("Producer has been interrupted");
    }

    private int makeDoughnut() throws InterruptedException {
        Integer doughnut = ++doughnutNumber;
        TimeUnit.SECONDS.sleep(1);
        return doughnut;
    }

}
