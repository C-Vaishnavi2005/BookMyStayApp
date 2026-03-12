import java.util.HashMap;
import java.util.Map;

abstract class Room {
    String type;
    double price;

    Room(String type, double price) {
        this.type = type;
        this.price = price;
    }

    void display(int available) {
        System.out.println("Room Type: " + type);
        System.out.println("Price: " + price);
        System.out.println("Available: " + available);
        System.out.println();
    }
}

class StandardRoom extends Room {
    StandardRoom() {
        super("Standard Room", 2000);
    }
}

class DeluxeRoom extends Room {
    DeluxeRoom() {
        super("Deluxe Room", 3500);
    }
}

class SuiteRoom extends Room {
    SuiteRoom() {
        super("Suite Room", 5000);
    }
}

class RoomInventory {
    private HashMap<String, Integer> inventory = new HashMap<>();

    void registerRoom(String type, int count) {
        inventory.put(type, count);
    }

    int getAvailability(String type) {
        return inventory.getOrDefault(type, 0);
    }
}

class SearchService {
    private RoomInventory inventory;
    private HashMap<String, Room> rooms = new HashMap<>();

    SearchService(RoomInventory inventory) {
        this.inventory = inventory;
    }

    void registerRoom(Room room) {
        rooms.put(room.type, room);
    }

    void searchAvailableRooms() {
        for (Map.Entry<String, Room> entry : rooms.entrySet()) {
            int available = inventory.getAvailability(entry.getKey());
            if (available > 0) {
                entry.getValue().display(available);
            }
        }
    }
}

public class BookMyStayApp {
    public static void main(String[] args) {
        RoomInventory inventory = new RoomInventory();

        inventory.registerRoom("Standard Room", 10);
        inventory.registerRoom("Deluxe Room", 0);
        inventory.registerRoom("Suite Room", 2);

        SearchService searchService = new SearchService(inventory);

        searchService.registerRoom(new StandardRoom());
        searchService.registerRoom(new DeluxeRoom());
        searchService.registerRoom(new SuiteRoom());

        searchService.searchAvailableRooms();
    }
}