public class Main {

    public static void main(String[] args) {
        Bakery bakery = new Bakery();
        Thread doughnutProducerThread = new Thread(new DoughnutProducer(bakery), "Doughnut producer");
        Thread doughnutConsumerThread = new Thread(new DoughnutConsumer(bakery), "Doughnut consumer");
        doughnutProducerThread.start();
        doughnutConsumerThread.start();
    }

}
