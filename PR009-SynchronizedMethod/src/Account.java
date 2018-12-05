public class Account {

    private float balance;

    public Account(float initialBalance) {
        this.balance = initialBalance;
    }

    public float getBalance() {
        return balance;
    }

    // Try and remove synchronized keyword and see what happens.
    public synchronized void deposit(float amount) {
        balance += amount;
        System.out.print(".");
    }

    // Try and remove synchronized keyword and see what happens.
    public synchronized void debit(float amount) {
        balance -= amount;
        System.out.print(".");
    }

}
