import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class OneBeerFriend implements Runnable {

    private final String name;
    private final Phaser phaser;
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
    private final Random random = new Random();

    public OneBeerFriend(String name, Phaser phaser) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(phaser);
        this.name = name;
        this.phaser = phaser;
    }

    @Override
    public void run() {
        phaser.register();
        try {
            goToPub();
        } catch (InterruptedException e) {
            System.out.printf("%s has been interrupted while going to the pub\n", name);
            return;
        }
        try {
            phaser.awaitAdvanceInterruptibly(phaser.arrive());
        } catch (InterruptedException e) {
            System.out.printf("%s has been interrupted while waiting for friends in the pub\n", name);
            return;
        }
        try {
            firstBeerInPub();
        } catch (InterruptedException e) {
            System.out.printf("%s has been interrupted while drinking the first beer\n", name);
            return;
        }
        try {
            phaser.awaitAdvanceInterruptibly(phaser.arrive());
        } catch (InterruptedException e) {
            System.out.printf("%s has been interrupted while waiting for friends to finish their fist beer\n", name);
            return;
        }
        // No more beers.
        phaser.arriveAndDeregister();
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

    private void goHome() throws InterruptedException {
        System.out.printf("%s -> %s is leaving the pub\n",
                simpleDateFormat.format(new Date()), name);
        TimeUnit.SECONDS.sleep(random.nextInt(5) + 1);
        System.out.printf("%s -> %s is at home\n",
                simpleDateFormat.format(new Date()), name);
    }

}
