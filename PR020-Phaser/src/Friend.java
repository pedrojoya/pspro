import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.concurrent.Phaser;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Friend implements Runnable {

    private final String name;
    private final Phaser phaser;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public Friend(String name, Phaser phaser) {
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
        try {
            secondBeerInPub();
        } catch (InterruptedException e) {
            System.out.printf("%s has been interrupted while drinking the second beer\n", name);
            return;
        }
        try {
            phaser.awaitAdvanceInterruptibly(phaser.arrive());
        } catch (InterruptedException e) {
            System.out.printf("%s has been interrupted while waiting for friends to finish their second beer\n", name);
            return;
        }
        try {
            goHome();
        } catch (InterruptedException e) {
            System.out.printf("%s has been interrupted while going back home\n", name);
        }
    }

    private void goToPub() throws InterruptedException {
        System.out.printf("%s -> %s is leaving home\n",
                LocalTime.now().format(dateTimeFormatter), name);
        TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(5) + 1);
        System.out.printf("%s -> %s has arrived in the pub\n",
                LocalTime.now().format(dateTimeFormatter), name);
    }

    private void firstBeerInPub() throws InterruptedException {
        TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(5) + 1);
        System.out.printf("%s -> %s has finished the first beer\n",
                LocalTime.now().format(dateTimeFormatter), name);
    }

    private void secondBeerInPub() throws InterruptedException {
        TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(5) + 1);
        System.out.printf("%s -> %s has finished the second beer\n",
                LocalTime.now().format(dateTimeFormatter), name);
    }

    private void goHome() throws InterruptedException {
        System.out.printf("%s -> %s is leaving the pub\n",
                LocalTime.now().format(dateTimeFormatter), name);
        TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(5) + 1);
        System.out.printf("%s -> %s is at home\n",
                LocalTime.now().format(dateTimeFormatter), name);
    }

}
