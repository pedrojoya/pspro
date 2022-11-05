import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.concurrent.*;

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
        } catch (InterruptedException | BrokenBarrierException | TimeoutException ignored) {
            System.out.printf("%s finishing\n", name);
        }
    }

    private void goToPub() throws InterruptedException, BrokenBarrierException, TimeoutException {
        System.out.printf("%s -> %s is leaving home\n",
                LocalTime.now().format(dateTimeFormatter), name);
        try {
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(5) + 1);
        } catch (InterruptedException e) {
            System.out.printf("%s has been interrupted while going to the pub\n", name);
            throw e;
        }
        System.out.printf("%s -> %s has arrived in the pub\n",
                LocalTime.now().format(dateTimeFormatter), name);
        try {
            cyclicBarrier.await(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.out.printf("%s has been interrupted while waiting for friends in the pub\n", name);
            throw e;
        } catch (BrokenBarrierException e) {
            System.out.printf("%s detects plan is over when arrived to pub\n", name);
            throw e;
        } catch (TimeoutException e) {
            System.out.printf("%s is tired of waiting for the resto to arrive in the pub\n", name);
            throw e;
        }
    }

    private void firstBeerInPub() throws InterruptedException, BrokenBarrierException, TimeoutException {
        try {
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(5) + 1);
        } catch (InterruptedException e) {
            System.out.printf("%s has been interrupted while drinking the first beer\n", name);
            throw e;
        }
        System.out.printf("%s -> %s has finished the first beer\n",
                LocalTime.now().format(dateTimeFormatter), name);
        try {
            cyclicBarrier.await(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.out.printf("%s has been interrupted while waiting for friends to finish their fist beer\n", name);
            throw e;
        } catch (BrokenBarrierException e) {
            System.out.printf("%s detects plan is over when finished first beer\n", name);
            throw e;
        } catch (TimeoutException e) {
            System.out.printf("%s is tired of waiting for the rest to finish first beer\n", name);
            throw e;
        }
    }

    private void secondBeerInPub() throws InterruptedException, BrokenBarrierException, TimeoutException {
        try {
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(5) + 1);
        } catch (InterruptedException e) {
            System.out.printf("%s has been interrupted while drinking the second beer\n", name);
            throw e;
        }
        System.out.printf("%s -> %s has finished the first beer\n",
                LocalTime.now().format(dateTimeFormatter), name);
        try {
            cyclicBarrier.await(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.out.printf("%s has been interrupted while waiting for friends to finish their second beer\n", name);
            throw e;
        } catch (BrokenBarrierException e) {
            System.out.printf("%s detects plan is over when finished second beer\n", name);
            throw e;
        } catch (TimeoutException e) {
            System.out.printf("%s is tired of waiting for the rest to finish second beer\n", name);
            throw e;
        }
    }

    private void goHome() throws InterruptedException {
        System.out.printf("%s -> %s is leaving the pub\n",
                LocalTime.now().format(dateTimeFormatter), name);
        try {
            TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(5) + 1);
        } catch (InterruptedException e) {
            System.out.printf("%s has been interrupted while going back home\n", name);
            throw e;
        }
        System.out.printf("%s -> %s is at home\n",
                LocalTime.now().format(dateTimeFormatter), name);
    }

}
