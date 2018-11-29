import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new SecondsHand(), "Seconds hand");
        thread.start();
        TimeUnit.SECONDS.sleep(5);
        thread.interrupt();
    }

}
