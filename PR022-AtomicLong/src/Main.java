import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        Account account = new Account(0);
        // Show initial balance.
        System.out.printf("%s -> Initial balance: %d€\n",
                simpleDateFormat.format(new Date()), account.getBalance());
        // Start both saver and consumer threads. Both threads share the same account.
        Thread saverThread = new Thread(new AccountSaver(account));
        saverThread.start();
        Thread consumerThread = new Thread(new AccountConsumer(account));
        consumerThread.start();
        // Wait for both threads to finish.
        saverThread.join();
        consumerThread.join();
        // Show final balance.
        System.out.printf("\n%s -> Final balance: %d€\n",
                simpleDateFormat.format(new Date()), account.getBalance());
    }

}
