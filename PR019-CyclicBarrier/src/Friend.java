import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Friend implements Runnable {

    private final String name;
    private final CyclicBarrier cyclicBarrier;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public Friend(String name, CyclicBarrier cyclicBarrier) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(cyclicBarrier);
        this.name = name;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        try {
            goToPub();
            firstBeerInPub();
            secondBeerInPub();
            goHome();
        } catch (InterruptedException | BrokenBarrierException ignored) {
            System.out.printf("%s -> %s finishing\n", LocalTime.now().format(dateTimeFormatter), name);
        }
    }

    private void goToPub() throws InterruptedException, BrokenBarrierException {
        boolean finished = false;
        System.out.printf("%s -> %s is leaving home\n",
                LocalTime.now().format(dateTimeFormatter), name);
        try {
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(5) + 1);
            System.out.printf("%s -> %s has arrived in the pub\n",
                    LocalTime.now().format(dateTimeFormatter), name);
            finished = true;
        } catch (InterruptedException e) {
            System.out.printf("%s -> %s has been interrupted while going to the pub\n", LocalTime.now().format(dateTimeFormatter), name);
            // As sleep clears interrupted flag, we mark it again as interrupted, so cyclicBarrier.await() breaks the barrier.
            // We don't rethrow InterruptedException because we want cyclicBarrier.await() to be executed.
            Thread.currentThread().interrupt();
        }
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            if (finished) {
                System.out.printf("%s -> %s was interrupted while waiting for friends in the pub\n", LocalTime.now().format(dateTimeFormatter), name);
            }
            throw e;
        } catch (BrokenBarrierException e) {
            System.out.printf("%s -> %s detects plan is over when arrived to pub\n", LocalTime.now().format(dateTimeFormatter), name);
            throw e;
        }
    }

    private void firstBeerInPub() throws InterruptedException, BrokenBarrierException {
        boolean finished = false;
        try {
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(5) + 1);
            System.out.printf("%s -> %s has finished the first beer\n",
                    LocalTime.now().format(dateTimeFormatter), name);
            finished = true;
        } catch (InterruptedException e) {
            System.out.printf("%s -> %s has been interrupted while drinking the first beer\n", LocalTime.now().format(dateTimeFormatter), name);
            Thread.currentThread().interrupt();
        }
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            if (finished) {
                System.out.printf("%s -> %s was interrupted while waiting for friends to finish their fist beer\n", LocalTime.now().format(dateTimeFormatter), name);
            }
            throw e;
        } catch (BrokenBarrierException e) {
            System.out.printf("%s -> %s detects plan is over when finished first beer\n", LocalTime.now().format(dateTimeFormatter), name);
            throw e;
        }
    }

    private void secondBeerInPub() throws InterruptedException, BrokenBarrierException {
        boolean finished = false;
        try {
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(5) + 1);
            System.out.printf("%s -> %s has finished the second beer\n",
                    LocalTime.now().format(dateTimeFormatter), name);
            finished = true;
        } catch (InterruptedException e) {
            System.out.printf("%s -> %s has been interrupted while drinking the second beer\n", LocalTime.now().format(dateTimeFormatter), name);
            Thread.currentThread().interrupt();
        }
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            if (finished) {
                System.out.printf("%s -> %s has been interrupted while waiting for friends to finish their second beer\n", LocalTime.now().format(dateTimeFormatter), name);
            }
            throw e;
        } catch (BrokenBarrierException e) {
            System.out.printf("%s -> %s detects plan is over when finished second beer\n", LocalTime.now().format(dateTimeFormatter), name);
            throw e;
        }
    }

    private void goHome() throws InterruptedException {
        System.out.printf("%s -> %s is leaving the pub\n",
                LocalTime.now().format(dateTimeFormatter), name);
        try {
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(5) + 1);
        } catch (InterruptedException e) {
            System.out.printf("%s -> %s has been interrupted while going back home\n", LocalTime.now().format(dateTimeFormatter), name);
            throw e;
        }
        System.out.printf("%s -> %s is at home\n",
                LocalTime.now().format(dateTimeFormatter), name);
    }

}
