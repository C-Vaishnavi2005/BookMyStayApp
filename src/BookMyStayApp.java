import java.util.HashMap;
import java.util.Map;

class RoomInventory {
    private HashMap<String, Integer> inventory = new HashMap<>();

    void registerRoom(String type, int count) {
        inventory.put(type, count);
    }

    int getAvailability(String type) {
        return inventory.getOrDefault(type, 0);
    }

    void updateAvailability(String type, int count) {
        if (inventory.containsKey(type)) {
            inventory.put(type, count);
        }
    }

    void displayInventory() {
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println("Room Type: " + entry.getKey() + " | Available: " + entry.getValue());
        }
    }
}

public class BookMyStayApp {
    public static void main(String[] args) {
        RoomInventory inventory = new RoomInventory();

        inventory.registerRoom("Standard Room", 10);
        inventory.registerRoom("Deluxe Room", 5);
        inventory.registerRoom("Suite Room", 2);

        inventory.displayInventory();

        inventory.updateAvailability("Standard Room", 8);

        System.out.println();
        inventory.displayInventory();
    }
}