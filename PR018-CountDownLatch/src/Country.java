import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Country implements Runnable {

    private final int participants;
    private final String countryName;
    private final Meeting meeting;
    private final Random random = new Random();

    public Country(int participants, Meeting meeting, String countryName) {
        if (participants < 1 || meeting == null || countryName == null) {
            throw new IllegalArgumentException();
        }
        this.participants = participants;
        this.countryName = countryName;
        this.meeting = meeting;
    }

    @Override
    public void run() {
        try {
            goToMeeting();
        } catch (InterruptedException e) {
            System.out.println("I've been interrupted while going to the meeting");
            return;
        }
        meeting.join(participants, countryName);
        try {
            makeProposals();
        } catch (InterruptedException e) {
            System.out.println("I've been interrupted while making proposals");
        }
    }

    private void goToMeeting() throws InterruptedException {
        TimeUnit.SECONDS.sleep(random.nextInt(5));
    }

    private void makeProposals() throws InterruptedException {
        TimeUnit.SECONDS.sleep(random.nextInt(3));
        meeting.propose(countryName);
    }

}
