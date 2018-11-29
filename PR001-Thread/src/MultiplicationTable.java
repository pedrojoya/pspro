class MultiplicationTable extends Thread {

    private final int number;

    MultiplicationTable(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.printf("%s: %d * %d = %d\n", getName(), number, i, i * number);
        }
    }

}
