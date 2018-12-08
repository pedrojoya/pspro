import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Doughnut implements Delayed {

    private static final long DONUT_GLAZE_DELAY_MILLIS = 3000;

    private final int number;
    private final long startTime;

    public Doughnut(int number) {
        this.number = number;
        this.startTime = System.currentTimeMillis() + DONUT_GLAZE_DELAY_MILLIS;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long diff = startTime - System.currentTimeMillis();
        return unit.convert(diff, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return (int) (this.startTime - ((Doughnut) o).startTime);
    }

}
