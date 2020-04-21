import java.util.concurrent.atomic.AtomicLong;

public class Account {

    private final AtomicLong balance;

    public Account(long initialBalance) {
        balance = new AtomicLong(initialBalance);
    }

    public long getBalance() {
        return balance.get();
    }

    public void deposit(long amount) {
        balance.addAndGet(amount);
    }

    public void debit(long amount) {
        balance.addAndGet(-amount);
    }

}
