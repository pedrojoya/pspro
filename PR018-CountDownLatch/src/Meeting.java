import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Meeting {

    private final MeetingSession meetingSession;
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    public Meeting(MeetingSession meetingSession) {
        this.meetingSession = meetingSession;
    }

    public void waitForItToStart() throws InterruptedException {
        meetingSession.waitForItToStart();
    }

    public boolean isStarted() {
        return meetingSession.isStarted();
    }

    public void join(int participants, String countryName) {
        System.out.printf("%s -> %d participants from %s have joined the meeting\n",
                LocalTime.now().format(dateTimeFormatter), participants, countryName);
        for (int i = 0; i < participants; i++) {
            meetingSession.join();
        }
    }

    public void propose(String countryName) {
        System.out.printf("%s -> Delegation from %s has made some proposals\n",
                LocalTime.now().format(dateTimeFormatter), countryName);
    }

}