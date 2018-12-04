import java.util.concurrent.TimeUnit;

public class Main {

    private static final int NORMAL_FRIENDS = 3;

    public static void main(String[] args) throws InterruptedException {
        int i;
        FriendsPhaser phaser = new FriendsPhaser();
        for (i = 0; i < NORMAL_FRIENDS; i++) {
            new Thread(new Friend("Friend #" + i, phaser), "Friend #" + i).start();
        }
        // This friend won't wait for anybody.
        new Thread(new ImpacientFriend("Friend #" + i, phaser), "Friend #" + i).start();
        i++;
        // This friend will drink just one beer and will go home.
        new Thread(new OneBeerFriend("Friend #" + i, phaser), "Friend #" + i).start();
        i++;
        // This friend will join be late, maybe when other friends are drinking.
        TimeUnit.SECONDS.sleep(9);
        new Thread(new TardyFriend("Friend #" + i, phaser), "Friend #" + i).start();
        i++;
        // This friend will join very very late.
        TimeUnit.SECONDS.sleep(12);
        new Thread(new TardyFriend("Friend #" + i, phaser), "Friend #" + i).start();
    }

}
