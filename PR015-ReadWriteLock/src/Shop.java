import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Shop implements Runnable {

    private final Product product;

    public Shop(Product product) {
        Objects.requireNonNull(product);
        this.product = product;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            try {
                product.updatePrice(20.0);
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                System.out.println("I've been interrupted while updating the price");
                return;
            }
        }
    }

}
