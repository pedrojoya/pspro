import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.DelayQueue;

public class Bakery {

    final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private final DelayQueue<Doughnut> tray = new DelayQueue<>();

    public void addToTray(Doughnut doughnut) {
        tray.put(doughnut);
        System.out.printf("%s -> Producer puts doughnut #%d on the tray\n",
                LocalTime.now().format(dateTimeFormatter), doughnut.getNumber());
    }

    public Doughnut extractFromTray() throws InterruptedException {
        System.out.printf("%s -> Consumer tries to extract a doughnut from tray\n",
                LocalTime.now().format(dateTimeFormatter));
        Doughnut doughnut = tray.take();
        System.out.printf("%s -> Consumer extracts doughnut #%d from tray\n",
                LocalTime.now().format(dateTimeFormatter), doughnut.getNumber());
        return doughnut;
    }

}
