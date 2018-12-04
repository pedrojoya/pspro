import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class DoughnutConsumer implements Runnable {

    private final Exchanger<List<Integer>> exchanger;
    private List<Integer> buffer = new ArrayList<>();

    public DoughnutConsumer(Exchanger<List<Integer>> exchanger) {
        Objects.requireNonNull(exchanger);
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        Integer doughnut;
        while (!Thread.currentThread().isInterrupted()) {
            // Exchage the empty buffer for a full one and then consume it.
            System.out.println("Consumer ready for exchange");
            try {
                buffer = exchanger.exchange(buffer, 20, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                System.out.println("Consumer has been interrupted while exchanging buffer");
                return;
            } catch (TimeoutException e) {
                System.out.println("Consumer can't wait for the producer anymore");
                return;
            }
            for (int i = 0; i < Main.BUFFER_SIZE; i++){
                doughnut = buffer.remove(0);
                try {
                    eat(doughnut);
                } catch (InterruptedException e) {
                    System.out.println("Consumer has been interrupted while eating");
                    return;
                }
            }
        }
        System.out.println("Consumer has been interrupted");
    }

    private void eat(int doughnut) throws InterruptedException {
        System.out.printf("Consumer is eating doughnut #%d\n", doughnut);
        TimeUnit.MILLISECONDS.sleep(500);
    }

}
