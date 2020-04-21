import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CountDownLatch;

public class Meeting implements Runnable {

    private final CountDownLatch countDownLatch;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public Meeting(int quorum) {
        countDownLatch = new CountDownLatch(quorum);
    }

    @Override
    public void run() {
        System.out.printf("%s -> Waiting for quorum to start the meeting\n",
                LocalTime.now().format(dateTimeFormatter));
        try {
            countDownLatch.await();
            System.out.printf("%s -> We have quorum. Meeting started...\n",
                    LocalTime.now().format(dateTimeFormatter));
        } catch (InterruptedException e) {
            System.out.println("Meeting has been interrupted while waiting to have quorum");
        }
    }

    public void join(int participants, String countryName) {
        System.out.printf("%s -> %d participants from %s have joined the meeting\n",
                LocalTime.now().format(dateTimeFormatter), participants, countryName);
        for (int i = 0; i < participants; i++) {
            countDownLatch.countDown();
        }
    }

    public void propose(String countryName) {
        System.out.printf("%s -> Delegation from %s has made some proposals\n",
                LocalTime.now().format(dateTimeFormatter), countryName);
    }

}
