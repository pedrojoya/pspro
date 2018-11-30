public class BoxOffice1Queue implements Runnable {

    private final Cinema cinema;
    private final int boxOffice;

    public BoxOffice1Queue(Cinema cinema, int boxOffice) {
        this.cinema = cinema;
        this.boxOffice = boxOffice;
    }

    @Override
    public void run() {
        try {
            cinema.buyTickets(boxOffice, 0, 3);
            cinema.buyTickets(boxOffice,0, 2);
            cinema.buyTickets(boxOffice,1, 2);
            cinema.returnTickets(boxOffice,0, 3);
            cinema.buyTickets(boxOffice,0, 5);
            cinema.buyTickets(boxOffice,1, 2);
            cinema.buyTickets(boxOffice,1, 2);
            cinema.buyTickets(boxOffice,1, 2);
        } catch (InterruptedException e) {
            System.out.println("Box office #%d has been closed");
        }
    }

}
