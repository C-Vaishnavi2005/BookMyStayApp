import java.util.LinkedList;
import java.util.Queue;

class Reservation {
    String guestName;
    String roomType;

    Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    void display() {
        System.out.println("Guest: " + guestName + " | Room Type: " + roomType);
    }
}

class BookingRequestQueue {
    private Queue<Reservation> queue = new LinkedList<>();

    void addRequest(Reservation reservation) {
        queue.add(reservation);
    }

    void displayQueue() {
        for (Reservation r : queue) {
            r.display();
        }
    }
}

public class BookMyStayApp {
    public static void main(String[] args) {
        BookingRequestQueue requestQueue = new BookingRequestQueue();

        requestQueue.addRequest(new Reservation("Arun", "Standard Room"));
        requestQueue.addRequest(new Reservation("Priya", "Deluxe Room"));
        requestQueue.addRequest(new Reservation("Rahul", "Suite Room"));

        requestQueue.displayQueue();
    }
}