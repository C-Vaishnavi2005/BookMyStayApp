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
}

class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();

    void registerRoom(String type, int count) {
        inventory.put(type, count);
    }

    void increment(String type) {
        inventory.put(type, inventory.getOrDefault(type, 0) + 1);
    }

    void display() {
        for (Map.Entry<String, Integer> e : inventory.entrySet()) {
            System.out.println(e.getKey() + " Available: " + e.getValue());
        }
    }
}

class BookingHistory {
    private Map<String, Reservation> history = new HashMap<>();

    void addReservation(Reservation r) {
        history.put(r.reservationId, r);
    }

    Reservation getReservation(String id) {
        return history.get(id);
    }

    void removeReservation(String id) {
        history.remove(id);
    }

    void display() {
        for (Reservation r : history.values()) {
            System.out.println(r.reservationId + " " + r.guestName + " " + r.roomType);
        }
    }
}

class CancellationService {
    private RoomInventory inventory;
    private BookingHistory history;
    private Stack<String> rollbackStack = new Stack<>();

    CancellationService(RoomInventory inventory, BookingHistory history) {
        this.inventory = inventory;
        this.history = history;
    }

    void cancel(String reservationId) {
        Reservation r = history.getReservation(reservationId);
        if (r == null) {
            System.out.println("Cancellation Failed: Reservation not found");
            return;
        }
        rollbackStack.push(r.reservationId);
        inventory.increment(r.roomType);
        history.removeReservation(reservationId);
        System.out.println("Reservation Cancelled: " + reservationId);
    }
}

public class BookMyStayApp {
    public static void main(String[] args) {
        RoomInventory inventory = new RoomInventory();
        inventory.registerRoom("Standard Room", 1);
        inventory.registerRoom("Deluxe Room", 0);

        BookingHistory history = new BookingHistory();
        Reservation r1 = new Reservation("ROOM-1", "Arun", "Standard Room");
        history.addReservation(r1);

        CancellationService service = new CancellationService(inventory, history);

        service.cancel("ROOM-1");

        inventory.display();
        history.display();
    }
}