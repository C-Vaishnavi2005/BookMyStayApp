import java.util.*;

class Reservation {
    String reservationId;
    String guestName;
    String roomType;

    Reservation(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }

    void display() {
        System.out.println("Reservation ID: " + reservationId);
        System.out.println("Guest: " + guestName);
        System.out.println("Room Type: " + roomType);
        System.out.println();
    }
}

class BookingHistory {
    private List<Reservation> history = new ArrayList<>();

    void addReservation(Reservation r) {
        history.add(r);
    }

    List<Reservation> getHistory() {
        return history;
    }
}

class BookingReportService {
    private BookingHistory history;

    BookingReportService(BookingHistory history) {
        this.history = history;
    }

    void displayAllBookings() {
        for (Reservation r : history.getHistory()) {
            r.display();
        }
    }
}

public class BookMyStayApp {
    public static void main(String[] args) {
        BookingHistory history = new BookingHistory();

        Reservation r1 = new Reservation("ROOM-1", "Arun", "Standard Room");
        Reservation r2 = new Reservation("ROOM-2", "Priya", "Deluxe Room");
        Reservation r3 = new Reservation("ROOM-3", "Rahul", "Suite Room");

        history.addReservation(r1);
        history.addReservation(r2);
        history.addReservation(r3);

        BookingReportService reportService = new BookingReportService(history);
        reportService.displayAllBookings();
    }
}