import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class Friend implements Runnable {

    private final String name;
    private final CyclicBarrier cyclicBarrier;
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
    private final Random random = new Random();

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
        } catch (InterruptedException e) {
            System.out.printf("%s has been interrupted while going to the pub\n", name);
            return;
        }
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            System.out.printf("%s has been interrupted while waiting for friends in the pub\n", name);
            return;
        } catch (BrokenBarrierException e) {
            System.out.printf("%s doesn't wait any more for friends in the pub because someone isn't coming\n", name);
        }
        try {
            firstBeerInPub();
        } catch (InterruptedException e) {
            System.out.printf("%s has been interrupted while drinking the first beer\n", name);
            return;
        }
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            System.out.printf("%s has been interrupted while waiting for friends to finish their fist beer\n", name);
            return;
        } catch (BrokenBarrierException e) {
            System.out.printf("%s doesn't wait any more for friends to finish their first beer because someone isn't going to finish it\n", name);
        }
        try {
            secondBeerInPub();
        } catch (InterruptedException e) {
            System.out.printf("%s has been interrupted while drinking the second beer\n", name);
            return;
        }
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            System.out.printf("%s has been interrupted while waiting for friends to finish their second beer\n", name);
            return;
        } catch (BrokenBarrierException e) {
            System.out.printf("%s doesn't wait any more for friends to finish their second beer because someone isn't going to finish it\n", name);
        }
        try {
            goHome();
        } catch (InterruptedException e) {
            System.out.printf("%s has been interrupted while going back home\n", name);
        }
    }

    private void goToPub() throws InterruptedException {
        System.out.printf("%s -> %s is leaving home\n",
                simpleDateFormat.format(new Date()), name);
        TimeUnit.SECONDS.sleep(random.nextInt(5) + 1);
        System.out.printf("%s -> %s has arrived in the pub\n",
                simpleDateFormat.format(new Date()), name);
    }

    private void firstBeerInPub() throws InterruptedException {
        TimeUnit.SECONDS.sleep(random.nextInt(5) + 1);
        System.out.printf("%s -> %s has finished the first beer\n",
                simpleDateFormat.format(new Date()), name);
    }

    private void secondBeerInPub() throws InterruptedException {
        TimeUnit.SECONDS.sleep(random.nextInt(5) + 1);
        System.out.printf("%s -> %s has finished the first beer\n",
                simpleDateFormat.format(new Date()), name);
    }

    private void goHome() throws InterruptedException {
        System.out.printf("%s -> %s is leaving the pub\n",
                simpleDateFormat.format(new Date()), name);
        TimeUnit.SECONDS.sleep(random.nextInt(5) + 1);
        System.out.printf("%s -> %s is at home\n",
                simpleDateFormat.format(new Date()), name);
    }

}
