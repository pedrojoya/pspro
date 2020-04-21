import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Product product = new Product(100.00);
        Thread[] clientThreads= new Thread[4];
        for (int i = 0; i < 4; i++) {
            clientThreads[i] = new Thread(new Client(product), "Client " + i);
        }
        Thread shopThread = new Thread(new Shop(product), "Shop");
        shopThread.start();
        // Wait to start some clients.
        TimeUnit.SECONDS.sleep(1);
        for (int i = 0; i < 2; i++){
            clientThreads[i].start();
        }
        // Wait to start the rest of the clients.
        TimeUnit.SECONDS.sleep(3);
        for (int i = 2; i < 4; i++){
            clientThreads[i].start();
        }
        // Try to check a client thread blocks the shop thread but not other clients threads.
        // Try to check the shop thread blocks client threads.
    }

}
