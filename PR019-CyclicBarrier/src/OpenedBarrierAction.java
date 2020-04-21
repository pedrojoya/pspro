import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class OpenedBarrierAction implements Runnable {

    private final DateTimeFormatter dateTimeFormatter =
            DateTimeFormatter.ofPattern("HH:mm:ss", Locale.getDefault());

    @Override
    public void run() {
        System.out.printf("%s -> Phase change (executed in %s)\n",
                LocalTime.now().format(dateTimeFormatter), Thread.currentThread().getName());
    }

}
