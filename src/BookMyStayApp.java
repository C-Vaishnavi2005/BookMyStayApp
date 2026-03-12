import java.io.*;
import java.util.*;

class Reservation implements Serializable {
    String reservationId;
    String guestName;
    String roomType;

    Reservation(String reservationId, String guestName, String roomType) {
        this.reservationId = reservationId;
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

class RoomInventory implements Serializable {
    Map<String, Integer> inventory = new HashMap<>();

    void registerRoom(String type, int count) {
        inventory.put(type, count);
    }

    void display() {
        for (Map.Entry<String, Integer> e : inventory.entrySet()) {
            System.out.println(e.getKey() + " Available: " + e.getValue());
        }
    }
}

class BookingHistory implements Serializable {
    List<Reservation> history = new ArrayList<>();

    void addReservation(Reservation r) {
        history.add(r);
    }

    void display() {
        for (Reservation r : history) {
            System.out.println(r.reservationId + " " + r.guestName + " " + r.roomType);
        }
    }
}

class PersistenceService {
    static void saveState(RoomInventory inventory, BookingHistory history, String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(inventory);
            out.writeObject(history);
            System.out.println("System state saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving state: " + e.getMessage());
        }
    }

    static Object[] loadState(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            RoomInventory inventory = (RoomInventory) in.readObject();
            BookingHistory history = (BookingHistory) in.readObject();
            System.out.println("System state loaded successfully.");
            return new Object[]{inventory, history};
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading state: " + e.getMessage());
            return null;
        }
    }
}

public class BookMyStayApp {
    public static void main(String[] args) {
        RoomInventory inventory = new RoomInventory();
        inventory.registerRoom("Standard Room", 2);
        inventory.registerRoom("Deluxe Room", 1);

        BookingHistory history = new BookingHistory();
        history.addReservation(new Reservation("ROOM-1", "Arun", "Standard Room"));
        history.addReservation(new Reservation("ROOM-2", "Priya", "Deluxe Room"));

        String filename = "bookmyapp_state.dat";

        PersistenceService.saveState(inventory, history, filename);

        Object[] restored = PersistenceService.loadState(filename);
        if (restored != null) {
            RoomInventory restoredInventory = (RoomInventory) restored[0];
            BookingHistory restoredHistory = (BookingHistory) restored[1];

            System.out.println("\nRestored Inventory:");
            restoredInventory.display();
            System.out.println("\nRestored Booking History:");
            restoredHistory.display();
        }
    }
}