package es.iessaladillo.pedrojoya.pspro.gar0302;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

class RedInfo {

    public static void main(String[] args) {
        ThreadPoolExecutor ejecutor = (ThreadPoolExecutor) Executors
                .newFixedThreadPool(5);
        for (int i = 1; i <= 50; i++) {
            Tarea tarea = new Tarea("192.168.1." + i);
            ejecutor.execute(tarea);
        }
        ejecutor.shutdown();
    }

}
