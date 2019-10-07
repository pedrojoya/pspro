class Main {

    private static final int NUMBER_OF_PILOTS = 10;

    public static void main(String[] args) {
        LightSystem lightSystem = new LightSystem();
        Thread raceDirectorThread = new Thread(new RaceDirector(lightSystem), "Race director");
        Thread[] pilots = new Thread[NUMBER_OF_PILOTS];
        for (int i = 0; i < NUMBER_OF_PILOTS; i++) {
            pilots[i] = new Thread(new Pilot(i, lightSystem), "Pilot " + i);
        }
        raceDirectorThread.start();
        for (int i = 0; i < NUMBER_OF_PILOTS; i++) {
            pilots[i].start();
        }
    }

}
