public class Account {

    private float balance;

    public Account(float initialBalance) {
        this.balance = initialBalance;
    }

    public float getBalance() {
        return balance;
    }

    public void deposit(float amount) {
        synchronized (this) {
            balance += amount;
        }
    }

    public void debit(int amount) {
        synchronized (this) {
            balance -= amount;
        }
    }

}
