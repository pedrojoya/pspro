public class Cinema {

    private final int[] movieTheatersCapacity;
    private final int boxOffices;
    private int[] availableSeats;
    private Object[] movieTheatersLock;

    public Cinema(int[] movieTheatersCapacity, int boxOffices) {
        if (movieTheatersCapacity == null || boxOffices < 1) {
            throw new IllegalArgumentException();
        }
        for (int aMovieTheatersCapacity : movieTheatersCapacity) {
            if (aMovieTheatersCapacity < 1) {
                throw new IllegalArgumentException();
            }
        }
        this.movieTheatersCapacity = movieTheatersCapacity;
        this.boxOffices = boxOffices;
        availableSeats = new int[movieTheatersCapacity.length];
        movieTheatersLock = new Object[movieTheatersCapacity.length];
        for (int i = 0; i < movieTheatersCapacity.length; i++) {
            movieTheatersLock[i] = new Object();
        }
        resetMovieTheaters();
    }

    private void resetMovieTheaters() {
        System.arraycopy(movieTheatersCapacity, 0, availableSeats, 0, movieTheatersCapacity.length);
    }

    public boolean buyTickets(int boxOffice, int movieTheater, int tickets) throws InterruptedException {
        if (movieTheater < 0 || movieTheater >= availableSeats.length || tickets < 1 || boxOffice < 1 || boxOffice > boxOffices) {
            throw new IllegalArgumentException();
        }
        synchronized (movieTheatersLock[movieTheater]) {
            if (tickets <= availableSeats[movieTheater]) {
                availableSeats[movieTheater] -= tickets;
                System.out.printf("Movie theater #%d: %d tickets bought in box office #%d\n", movieTheater,
                        tickets, boxOffice);
                Thread.sleep(1000);
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean returnTickets(int boxOffice, int movieTheater, int tickets) {
        if (movieTheater < 0 || movieTheater >= availableSeats.length || tickets < 1 || boxOffice < 1 || boxOffice > boxOffices) {
            throw new IllegalArgumentException();
        }
        synchronized (movieTheatersLock[movieTheater]) {
            if (tickets + availableSeats[movieTheater] <= movieTheatersCapacity[movieTheater]) {
                availableSeats[movieTheater] += tickets;
                System.out.printf("Movie theater %d: %d tickets returned in box office #%d\n", movieTheater, tickets, boxOffice);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return true;
            } else {
                return false;
            }
        }
    }

    public int getAvailableSeats(int movieTheater) {
        return availableSeats[movieTheater];
    }

}
