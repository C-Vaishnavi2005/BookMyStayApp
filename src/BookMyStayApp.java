import java.util.*;

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

    void registerRoom(String type, int count) {
        inventory.put(type, count);
    }

    boolean isAvailable(String type) {
        return inventory.getOrDefault(type, 0) > 0;
    }

    void decrement(String type) {
        inventory.put(type, inventory.get(type) - 1);
    }
}

class BookingService {
    private Queue<Reservation> requestQueue = new LinkedList<>();
    private RoomInventory inventory;
    private Set<String> allocatedRoomIds = new HashSet<>();
    private int idCounter = 1;

    BookingService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    void addRequest(Reservation r) {
        requestQueue.add(r);
    }

    void processRequests() {
        while (!requestQueue.isEmpty()) {
            Reservation r = requestQueue.poll();
            if (inventory.isAvailable(r.roomType)) {
                String roomId = "ROOM-" + idCounter++;
                allocatedRoomIds.add(roomId);
                inventory.decrement(r.roomType);
                System.out.println("Reservation Confirmed");
                System.out.println("Guest: " + r.guestName);
                System.out.println("Room Type: " + r.roomType);
                System.out.println("Room ID: " + roomId);
                System.out.println();
            } else {
                System.out.println("Reservation Failed for " + r.guestName);
                System.out.println("Room Type Unavailable: " + r.roomType);
                System.out.println();
            }
        }
    }
}

public class BookMyStayApp {
    public static void main(String[] args) {
        RoomInventory inventory = new RoomInventory();
        inventory.registerRoom("Standard Room", 2);
        inventory.registerRoom("Deluxe Room", 1);

        BookingService service = new BookingService(inventory);

        service.addRequest(new Reservation("Arun", "Standard Room"));
        service.addRequest(new Reservation("Priya", "Standard Room"));
        service.addRequest(new Reservation("Rahul", "Standard Room"));
        service.addRequest(new Reservation("Meena", "Deluxe Room"));

        service.processRequests();
    }
}