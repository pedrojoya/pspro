public class Account {

    private float balance;

    public Account(float initialBalance) {
        this.balance = initialBalance;
    }

    public float getBalance() {
        return balance;
    }

    // Try to remove synchronized keyword and see what happens.
    public void deposit(float amount) {
        balance += amount;
//        System.out.print(".");
    }

    // Try to remove synchronized keyword and see what happens.
    public void debit(float amount) {
        balance -= amount;
//        System.out.print(".");
    }

}
