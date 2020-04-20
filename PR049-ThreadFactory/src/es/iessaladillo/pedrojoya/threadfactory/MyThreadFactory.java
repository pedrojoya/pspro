package es.iessaladillo.pedrojoya.threadfactory;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.concurrent.ThreadFactory;

public class MyThreadFactory implements ThreadFactory {

    private final String baseName;
    private final ArrayList<String> log = new ArrayList<>();
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private int count = 0;

    public MyThreadFactory(String baseName) {
        this.baseName = baseName;
    }

    @Override
    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable, baseName + "-" + count++);
        log.add(LocalTime.now().format(dateTimeFormatter) + " -> " + thread.getName() + " created");
        return thread;
    }

    public String getLog() {
        return String.join("\n", log);
    }

}
