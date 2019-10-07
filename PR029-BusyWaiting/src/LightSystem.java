import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class LightSystem {

    public enum Color {
        RED, YELLOW, GREEN
    }

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    private volatile Color currentColor = Color.RED;

    void setColor(Color newColor) {
        currentColor = newColor;
        System.out.printf("%s - Light system in %s\n", LocalDateTime.now().format(dateTimeFormatter), newColor);
    }

    Color getColor() {
        return currentColor;
    }

}
