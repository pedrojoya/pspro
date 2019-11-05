package es.iessaladillo.pedrojoya.fixedthreadpool;

class Main {

    public static void main(String[] args) {
        Server server = new Server();
        for (int i = 0; i < 50; i++) {
            Task task = new Task("Task " + i);
            server.execute(task);
            try {
                // The less time you sleep the greater the thread pool size gets.
                // Try and reduce the time sleeping and see whats happens to thread pool size.
                Thread.sleep(100);
            } catch (InterruptedException e) {
                return;
            }
        }
        try {
            server.shutdown();
            // Try shutdownNow instead and see what happens.
            // server.shutdownNow();
        } catch (InterruptedException exception) {
            return;
        }
        Task task = new Task("Task sent after shutdown");
        server.execute(task);
    }

}
