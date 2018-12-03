import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

public class Meeting implements Runnable {

    private final CountDownLatch countDownLatch;
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");

    public Meeting(int quorum) {
        countDownLatch = new CountDownLatch(quorum);
    }

    @Override
    public void run() {
        System.out.printf("%s -> Waiting for quorum to start the meeting\n",
                simpleDateFormat.format(new Date()));
        try {
            countDownLatch.await();
            System.out.printf("%s -> We have quorum. Meeting started...\n",
                    simpleDateFormat.format(new Date()));
        } catch (InterruptedException e) {
            System.out.println("Meeting has been interrupted while waiting to have quorum");
        }
    }

    public void join(int participants, String countryName) {
        System.out.printf("%s -> %d participants from %s have joined the meeting\n",
                simpleDateFormat.format(new Date()), participants, countryName);
        for (int i = 0; i < participants; i++) {
            countDownLatch.countDown();
        }
    }

    public void propose(String countryName) {
        System.out.printf("%s -> Delegation from %s has made some proposals\n",
                simpleDateFormat.format(new Date()), countryName);
    }

}
