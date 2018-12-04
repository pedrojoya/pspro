import java.util.List;
import java.util.concurrent.Exchanger;

public class Main {

    public static final int BUFFER_SIZE = 10;

    public static void main(String[] args) {
        Exchanger<List<Integer>> exchanger = new Exchanger<>();
        Thread doughnutProducerThread = new Thread(new DoughnutProducer(exchanger), "Doughnut producer");
        Thread doughnutConsumerThread = new Thread(new DoughnutConsumer(exchanger), "Doughnut consumer");
        doughnutProducerThread.start();
        doughnutConsumerThread.start();
    }

}
