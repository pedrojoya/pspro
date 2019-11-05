import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.ThreadLocalRandom;

public class ServerConnection implements Runnable {

    private final int connectionNumber;
    private final Socket socket;

    public ServerConnection(int connectionNumber, Socket socket) {
        this.connectionNumber = connectionNumber;
        this.socket = socket;
    }

    @Override
    public void run() {
        try (ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream input = new ObjectInputStream(socket.getInputStream())
        ) {
            Message receivedMessage;
            while ((receivedMessage = receiveMessage(input)) != null){
                sendConfirmationMessage(output, receivedMessage);
            }
        } catch (IOException e) {
            System.out.printf("Server: connection #%d closed\n", connectionNumber);
        } catch (ClassNotFoundException e) {
            System.out.printf("Server: Can't read message in connection #%d\n", connectionNumber);
        } catch (InterruptedException ignored) {
        }
    }

    private Message receiveMessage(ObjectInputStream input) throws IOException, ClassNotFoundException {
        Message message = (Message) input.readObject();
        if (message != null) {
            System.out.printf("Server - Message from %s: %s\n", message.getAuthor(), message.getContent());
        }
        return message;
    }

    private void sendConfirmationMessage(ObjectOutputStream output, Message message) throws IOException, InterruptedException {
        Thread.sleep(ThreadLocalRandom.current().nextInt(1, 10) * 1000);
        output.writeObject(new Message("Server", "Received message from " + message.getAuthor() + ": " + message.getContent()));
    }

}
