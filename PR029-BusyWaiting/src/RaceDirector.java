import java.util.Objects;

class RaceDirector implements Runnable {

    private final LightSystem lightSystem;

    RaceDirector(LightSystem lightSystem) {
        Objects.requireNonNull(lightSystem);
        this.lightSystem = lightSystem;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
            lightSystem.setColor(LightSystem.Color.YELLOW);
            Thread.sleep(3000);
            lightSystem.setColor(LightSystem.Color.GREEN);
        } catch (InterruptedException ignored) {
        }
    }

}
