import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class SecondsHand implements Runnable {

    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println(LocalDateTime.now().format(dateTimeFormatter));
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
