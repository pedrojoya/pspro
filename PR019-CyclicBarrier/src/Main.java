import java.util.concurrent.CyclicBarrier;

public class Main {

    private static final int NUMBER_OF_FRIENDS = 3;

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(NUMBER_OF_FRIENDS,
                new OpenedBarrierAction());
        for (int i = 0; i < NUMBER_OF_FRIENDS; i++) {
            new Thread(new Friend("Friend #" + i, cyclicBarrier), "Friend #" + i).start();
        }
    }

}
