public class AccountSaver implements Runnable {

    private final Account account;

    public AccountSaver(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100000; i++) {
            account.deposit(10);
        }
    }

}
