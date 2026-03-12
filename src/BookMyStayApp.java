import java.util.*;
import java.util.concurrent.*;

class Reservation {
    String guestName;
    String roomType;

    Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();

    synchronized void registerRoom(String type, int count) {
        inventory.put(type, count);
    }

    synchronized boolean allocateRoom(String type) {
        int available = inventory.getOrDefault(type, 0);
        if (available > 0) {
            inventory.put(type, available - 1);
            return true;
        }
        return false;
    }

    synchronized void display() {
        for (Map.Entry<String, Integer> e : inventory.entrySet()) {
            System.out.println(e.getKey() + " Available: " + e.getValue());
        }
    }
}

class BookingProcessor implements Runnable {
    private Reservation reservation;
    private RoomInventory inventory;

    BookingProcessor(Reservation reservation, RoomInventory inventory) {
        this.reservation = reservation;
        this.inventory = inventory;
    }

    public void run() {
        synchronized (inventory) {
            if (inventory.allocateRoom(reservation.roomType)) {
                System.out.println("Booking Confirmed: " + reservation.guestName + " -> " + reservation.roomType);
            } else {
                System.out.println("Booking Failed: " + reservation.guestName + " -> " + reservation.roomType + " (No availability)");
            }
        }
    }
}

public class BookMyStayApp {
    public static void main(String[] args) throws InterruptedException {
        RoomInventory inventory = new RoomInventory();
        inventory.registerRoom("Standard Room", 2);
        inventory.registerRoom("Deluxe Room", 1);

        List<Reservation> requests = Arrays.asList(
                new Reservation("Arun", "Standard Room"),
                new Reservation("Priya", "Standard Room"),
                new Reservation("Rahul", "Standard Room"),
                new Reservation("Meena", "Deluxe Room")
        );

        List<Thread> threads = new ArrayList<>();
        for (Reservation r : requests) {
            Thread t = new Thread(new BookingProcessor(r, inventory));
            threads.add(t);
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }

        System.out.println("\nFinal Inventory State:");
        inventory.display();
    }
}