import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

class Pilot implements Runnable {

    private final int number;
    private final LightSystem lightSystem;

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    Pilot(int number, LightSystem lightSystem) {
        Objects.requireNonNull(lightSystem);
        this.number = number;
        this.lightSystem = lightSystem;
    }

    @Override
    public void run() {
        warmup();
        while (!Thread.currentThread().isInterrupted() && lightSystem.getColor() != LightSystem.Color.GREEN) {
            Thread.onSpinWait();
        }
        if (!Thread.currentThread().isInterrupted()) {
            start();
        }
    }

    private void start() {
        System.out.printf("%s - Pilot %d starts the race!!!\n", LocalDateTime.now().format(dateTimeFormatter), number);
    }

    private void warmup() {
        System.out.printf("%s - Pilot %d is warming up car engine\n", LocalDateTime.now().format(dateTimeFormatter), number);
    }

}
