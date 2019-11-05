package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {

    private final int port;
    private int connections = 0;

    public Server(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while(!Thread.currentThread().isInterrupted()) {
                Socket socket = serverSocket.accept();
                new Thread(new ServerConnection(++connections, socket)).start();
            }
        } catch (IOException e) {
            System.out.println("Server: Input / Output error in server socket");
        }
    }

}
