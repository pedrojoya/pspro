public class AccountConsumer implements Runnable {

    private final Account account;

    public AccountConsumer(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100000; i++) {
            account.debit(5);
        }
    }

}
