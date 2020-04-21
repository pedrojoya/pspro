public class Main {

    private static final int BOX_OFFICES = 2;
    private static final int MOVIE_THEATER_CAPACITY = 20;

    public static void main(String[] args) {
        int[] movieTheaters = {MOVIE_THEATER_CAPACITY, MOVIE_THEATER_CAPACITY};
        Cinema cinema = new Cinema(movieTheaters, BOX_OFFICES);
        Thread boxOffice1Thread = new Thread(new BoxOffice1Queue(cinema, 1), "Box office 1");
        Thread boxOffice2Thread = new Thread(new BoxOffice2Queue(cinema, 2), "Box office 2");
        boxOffice1Thread.start();
        boxOffice2Thread.start();
        try {
            boxOffice1Thread.join();
            boxOffice2Thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < movieTheaters.length; i++) {
            System.out.printf("Movie theater %d: %d seats available\n", i + 1,
                    cinema.getAvailableSeats(i));
        }
    }

}
