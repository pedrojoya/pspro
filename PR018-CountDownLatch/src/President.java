import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class President implements Runnable {

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private final MeetingSession meetingSession;

    public President(MeetingSession meetingSession) {
        this.meetingSession = meetingSession;
    }

    @Override
    public void run() {
        System.out.printf("%s -> President waiting for quorum to start the meeting\n", LocalTime.now().format(dateTimeFormatter));
        try {
            meetingSession.waitForQuorum();
            System.out.printf("%s -> President says we have quorum. Meeting started...\n", LocalTime.now().format(dateTimeFormatter));
        } catch (InterruptedException e) {
            System.out.println("President has been interrupted while waiting to have quorum");
            return;
        }
        meetingSession.startMeeting();
    }

}