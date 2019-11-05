import message.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.ThreadLocalRandom;

public class Client implements Runnable {

    private final int clientNumber;
    private final String serverAddress;
    private final int serverPort;

    public Client(int clientNumber, String serverAddress, int serverPort) {
        this.clientNumber = clientNumber;
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    @Override
    public void run() {
        try (Socket socket = new Socket(serverAddress, serverPort);
             ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream input = new ObjectInputStream(socket.getInputStream())
        ) {
            sendMessage(output);
            receiveMessage(input);
            closeConnection();
        } catch (IOException e) {
            showConnectionError();
        } catch (ClassNotFoundException e) {
            showMessageFormatError();
        } catch (InterruptedException ignored) {
        }
    }

    private void sendMessage(ObjectOutputStream output) throws InterruptedException, IOException {
        Thread.sleep(ThreadLocalRandom.current().nextInt(1, 10) * 1000);
        output.writeObject(new Message("Client #" + clientNumber, "Hello from client " + clientNumber));
    }

    private void receiveMessage(ObjectInputStream input) throws IOException, ClassNotFoundException {
        Message message = (Message) input.readObject();
        System.out.printf("Client #%d - message.Message from %s: %s\n", clientNumber, message.getAuthor(), message.getContent());
    }

    private void closeConnection() throws InterruptedException {
        Thread.sleep(ThreadLocalRandom.current().nextInt(1, 10) * 1000);
    }

    private void showConnectionError() {
        System.out.printf("Client #%d: Can't connect with server in %s:%d\n", clientNumber, serverAddress, serverPort);
    }

    private void showMessageFormatError() {
        System.out.printf("Client #%d: Incorrect message format\n", clientNumber);
    }

}
