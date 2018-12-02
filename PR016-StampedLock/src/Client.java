import java.util.Objects;

public class Client implements Runnable {

    private final Product product;

    public Client(Product product) {
        Objects.requireNonNull(product);
        this.product = product;
    }

    @Override
    public void run() {
        try {
            @SuppressWarnings("unused")
            double price = product.getPrice();
        } catch (InterruptedException e) {
            System.out.println("I've been interrupted while consulting the price");
        }
    }

}
