import java.util.concurrent.CyclicBarrier;

public class Main {

    private static final int NUMBER_OF_FRIENDS = 3;

    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(NUMBER_OF_FRIENDS, new OpenedBarrierAction());
        Thread[] friends = new Thread[NUMBER_OF_FRIENDS];
        for (int i = 0; i < NUMBER_OF_FRIENDS; i++) {
            friends[i] = new Thread(new Friend("Friend #" + i, cyclicBarrier), "Friend #" + i);
            friends[i].start();
        }
        // Uncomment these lines to see what happens if first friend is interrupted
        // Thread.sleep(8000);
        // friends[0].interrupt();
    }

}
