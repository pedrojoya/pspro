import java.util.concurrent.atomic.DoubleAdder;

public class Account {

    private final DoubleAdder balance = new DoubleAdder();

    public Account(double initialBalance) {
        balance.add(initialBalance);
    }

    public double getBalance() {
        return balance.doubleValue();
    }

    public void deposit(long amount) {
        balance.add(amount);
    }

    public void debit(long amount) {
        balance.add(-amount);
    }

}
