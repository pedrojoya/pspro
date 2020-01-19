import client.Client;
import server.Server;

class Main {

    private static final int SERVER_PORT = 60000;
    private static final int NUMBER_OF_CLIENTS = 10;

    public static void main(String[] args) {
        String serverAddress = "localhost";
        Thread server = new Thread(new Server(SERVER_PORT));
        server.start();
        Thread[] clients = new Thread[NUMBER_OF_CLIENTS];
        for (int i = 0; i < NUMBER_OF_CLIENTS; i++) {
            clients[i] = new Thread(new Client(i + 1, serverAddress, SERVER_PORT));
            clients[i].start();
        }

    }

}
