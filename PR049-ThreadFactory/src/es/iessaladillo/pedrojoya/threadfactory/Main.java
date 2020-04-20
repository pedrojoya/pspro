package es.iessaladillo.pedrojoya.threadfactory;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        final MyThreadFactory threadFactory = new MyThreadFactory("MyThreadFactory");
        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Thread thread = threadFactory.newThread(new Task());
            threads.add(thread);
            thread.start();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException exception) {
                return;
            }
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException exception) {
                return;
            }
        }
        System.out.printf("\n\nMyThreadFactory Log\n\n%s", threadFactory.getLog());
    }

}
