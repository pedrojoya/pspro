import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        Account account = new Account(0);
        // Show initial balance.
        System.out.printf("%s -> Initial balance: %.2f€\n",
                LocalTime.now().format(dateTimeFormatter), account.balance);
        // Start both saver and consumer threads. Both threads share the same account.
        Thread saverThread = new Thread(new AccountSaver(account));
        saverThread.start();
        Thread consumerThread = new Thread(new AccountConsumer(account));
        consumerThread.start();
        // Wait for both threads to finish.
        saverThread.join();
        consumerThread.join();
        // Show final balance.
        System.out.printf("\n%s -> Final balance: %.2f€\n",
                LocalTime.now().format(dateTimeFormatter), account.balance);
    }

}
