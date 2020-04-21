import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Phaser;

public class FriendsPhaser extends Phaser {

    public static final int ARRIVE_TO_PUB_PHASE = 0;
    public static final int FINISH_FIST_BEER_PHASE = 1;
    public static final int FINISH_SECOND_BEER_PHASE = 2;

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Override
    protected boolean onAdvance(int phase, int registeredParties) {
        switch (phase) {
            case ARRIVE_TO_PUB_PHASE:
                System.out.printf("%s -> All %d friends arrived to pub (executed in %s)\n",
                        LocalTime.now().format(dateTimeFormatter), registeredParties,
                        Thread.currentThread().getName());
                break;
            case FINISH_FIST_BEER_PHASE:
                System.out.printf("%s -> All %d friends finished their first beer (executed in %s)\n",
                        LocalTime.now().format(dateTimeFormatter), registeredParties,
                        Thread.currentThread().getName());
                break;
            case FINISH_SECOND_BEER_PHASE:
                System.out.printf("%s -> All %d friends finished their second beer (executed in %s)\n",
                        LocalTime.now().format(dateTimeFormatter), registeredParties,
                        Thread.currentThread().getName());
                // The phaser is marked as terminated.
                return true;
        }
        return super.onAdvance(phase, registeredParties);
    }

}
