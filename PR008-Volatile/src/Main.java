public class Main {

    // Try and remove volatile keyword and run. See what happens.
    private static volatile int value = 0;

    public static void main(String[] args) {
        new ChangeListener().start();
        new ChangeMaker().start();
    }

    static class ChangeListener extends Thread {
        @Override
        public void run() {
            int localValue = value;
            // JVM can't update cache value of "value" variable, because is occupied
            // permforming the infinite loop. So we have to define the variable as volatile
            // to ensure it's cache value is updated when updated from another thread.
            while (localValue < 5) {
                if (localValue != value) {
                    System.out.printf("Detected new value: %d\n", value);
                    localValue = value;
                }
            }
        }
    }

    static class ChangeMaker extends Thread {
        @Override
        public void run() {
            int localValue = value;
            while (value < 5) {
                System.out.printf("Incrementing value to %d\n", localValue + 1);
                value = ++localValue;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    return;
                }
            }
        }
    }

}
