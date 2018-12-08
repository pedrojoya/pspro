import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.DelayQueue;

public class Bakery {

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
    private final DelayQueue<Doughnut> tray = new DelayQueue<>();

    public void addToTray(Doughnut doughnut) {
        tray.put(doughnut);
        System.out.printf("%s -> Producer puts doughnut #%d on the tray\n", simpleDateFormat.format(new Date()), doughnut.getNumber());
    }

    public Doughnut extractFromTray() throws InterruptedException {
        System.out.printf("%s -> Consumer tries to extract a doughnut from tray\n", simpleDateFormat.format(new Date()));
        Doughnut doughnut = tray.take();
        System.out.printf("%s -> Consumer extracts doughnut #%d from tray\n", simpleDateFormat.format(new Date()), doughnut.getNumber());
        return doughnut;
    }

}
