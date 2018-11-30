public class BoxOffice2Queue implements Runnable {

    private final Cinema cinema;
    private final int boxOffice;

    public BoxOffice2Queue(Cinema cinema, int boxOffice) {
        this.cinema = cinema;
        this.boxOffice = boxOffice;
    }

    @Override
    public void run() {
        try {
            cinema.buyTickets(boxOffice, 1, 2);
            cinema.buyTickets(boxOffice, 1, 4);
            cinema.buyTickets(boxOffice, 0, 2);
            cinema.buyTickets(boxOffice, 0, 1);
            cinema.returnTickets(boxOffice, 1, 2);
            cinema.buyTickets(boxOffice, 0, 3);
            cinema.buyTickets(boxOffice, 1, 2);
            cinema.buyTickets(boxOffice, 0, 2);
        } catch (InterruptedException e) {
            System.out.printf("Box office #%d has been closed\n", boxOffice);
        }
    }

}
