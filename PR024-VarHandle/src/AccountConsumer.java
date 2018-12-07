import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;

public class AccountConsumer implements Runnable {

    private final Account account;

    public AccountConsumer(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        VarHandle varHandle;
        try {
            varHandle = MethodHandles.lookup().in(Account.class).findVarHandle(Account.class, "balance", double.class);
            for (int i = 0; i < 10000; i++) {
                varHandle.getAndAdd(account, -5);
                System.out.print(".");
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
