    import java.util.concurrent.ArrayBlockingQueue;

    public class Bakery {

        private static final int TRAY_CAPACITY = 10;

        private final ArrayBlockingQueue<Integer> tray = new ArrayBlockingQueue<>(TRAY_CAPACITY);

        public void addToTray(Integer doughnut) throws InterruptedException {
            tray.put(doughnut);
            System.out.printf("Producer puts doughnut #%d on the tray\n", doughnut);
        }

        public Integer extractFromTray() throws InterruptedException {
            Integer doughnut = tray.take();
            System.out.printf("Consumer extracts doughnut #%d from tray\n", doughnut);
            return doughnut;
        }

    }
