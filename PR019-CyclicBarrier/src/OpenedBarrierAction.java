import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class OpenedBarrierAction implements Runnable {

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());

    @Override
    public void run() {
        System.out.printf("%s -> Phase change (executed in %s)\n",
                simpleDateFormat.format(new Date()), Thread.currentThread().getName());
    }

}
