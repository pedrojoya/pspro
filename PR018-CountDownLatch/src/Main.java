import java.util.Random;

public class Main {

    private static final int QUORUM = 10;

    public static void main(String[] args) {
        Random random = new Random();
        Meeting meeting = new Meeting(QUORUM);
        new Thread(meeting).start();
        for (int i = 0; i < 10; i++) {
            new Thread(new Country(random.nextInt(5) + 1, meeting, "Country #" + i)).start();
        }
    }

}
