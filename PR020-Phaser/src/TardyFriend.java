import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class TardyFriend implements Runnable {

    private final String name;
    private final Phaser phaser;
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
    private final Random random = new Random();

    public TardyFriend(String name, Phaser phaser) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(phaser);
        this.name = name;
        this.phaser = phaser;
    }

    @Override
    public void run() {
        if (!phaser.isTerminated()) {
            int joinPhase = phaser.register();
            System.out.printf("%s -> %s has joined friends in phase #%d\n",
                    simpleDateFormat.format(new Date()), name, joinPhase);
            try {
                goToPub();
            } catch (InterruptedException e) {
                System.out.printf("%s has been interrupted while going to the pub\n", name);
                return;
            }
            // Tardy friends shouln't do arrive on previous phases in order not to
            // interfere with the synchonization process or the current phase of the
            // phaser.
            if (joinPhase <= FriendsPhaser.ARRIVE_TO_PUB_PHASE) {
                try {
                    phaser.awaitAdvanceInterruptibly(phaser.arrive());
                } catch (InterruptedException e) {
                    System.out.printf("%s has been interrupted while waiting for friends in the pub\n", name);
                    return;
                }
            }
            try {
                firstBeerInPub();
            } catch (InterruptedException e) {
                System.out.printf("%s has been interrupted while drinking the first beer\n", name);
                return;
            }
            if (joinPhase <= FriendsPhaser.FINISH_FIST_BEER_PHASE) {
                try {
                    phaser.awaitAdvanceInterruptibly(phaser.arrive());
                } catch (InterruptedException e) {
                    System.out.printf("%s has been interrupted while waiting for friends to finish their fist beer\n", name);
                    return;
                }
            }
            try {
                secondBeerInPub();
            } catch (InterruptedException e) {
                System.out.printf("%s has been interrupted while drinking the second beer\n", name);
                return;
            }
            if (joinPhase <= FriendsPhaser.FINISH_SECOND_BEER_PHASE) {
                try {
                    phaser.awaitAdvanceInterruptibly(phaser.arrive());
                } catch (InterruptedException e) {
                    System.out.printf("%s has been interrupted while waiting for friends to finish their second beer\n", name);
                    return;
                }
            }
            try {
                goHome();
            } catch (InterruptedException e) {
                System.out.printf("%s has been interrupted while going back home\n", name);
            }
        } else {
            System.out.printf("%s -> %s called his friends too late\n",
                    simpleDateFormat.format(new Date()), name);
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
        System.out.printf("%s -> %s has finished the second beer\n",
                simpleDateFormat.format(new Date()), name);
    }

    private void goHome() throws InterruptedException {
        System.out.printf("%s -> %s is leaving the pub\n",
                simpleDateFormat.format(new Date()), name);
        TimeUnit.SECONDS.sleep(random.nextInt(5) + 1);
        System.out.printf("%s -> %s is at home\n",
                simpleDateFormat.format(new Date()), name);
    }

    @SuppressWarnings("unused")
    private void awaitPhase(Phaser phaser, int currentPhase, int expectedPhase) {
        while (currentPhase < expectedPhase && !phaser.isTerminated()) {
            System.out.printf("%s -> %s is waiting phase #%d to finish\n",
                    simpleDateFormat.format(new Date()), name, currentPhase);
            currentPhase = phaser.arriveAndAwaitAdvance();
        }
    }

}
