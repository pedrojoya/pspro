import java.util.concurrent.CountDownLatch;

public class MeetingSession {

    private final CountDownLatch quorumCountDownLatch;
    private final CountDownLatch startingCountDown = new CountDownLatch(1);

    public MeetingSession(int quorum) {
        quorumCountDownLatch = new CountDownLatch(quorum);
    }

    public void waitForQuorum() throws InterruptedException {
        quorumCountDownLatch.await();
    }

    public void startMeeting() {
        startingCountDown.countDown();
    }

    public void waitForItToStart() throws InterruptedException {
        startingCountDown.await();
    }

    public boolean isStarted() {
        return startingCountDown.getCount() <= 0;
    }

    public void join() {
        quorumCountDownLatch.countDown();
    }


}
