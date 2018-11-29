import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class SecondsHand implements Runnable {

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println(simpleDateFormat.format(new Date()));
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("I've been interrupted while sleeping");
                return;
            }
        }
        System.out.println("I've been interrupted");
    }

}
