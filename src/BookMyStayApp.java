import java.util.HashMap;
import java.util.Map;

class InvalidBookingException extends Exception {
    InvalidBookingException(String message) {
        super(message);
    }
}

class RoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();

    void registerRoom(String type, int count) {
        inventory.put(type, count);
    }

    boolean roomExists(String type) {
        return inventory.containsKey(type);
    }

    int getAvailability(String type) {
        return inventory.getOrDefault(type, 0);
    }
}

class InvalidBookingValidator {
    void validate(String guestName, String roomType, RoomInventory inventory) throws InvalidBookingException {
        if (guestName == null || guestName.trim().isEmpty()) {
            throw new InvalidBookingException("Guest name cannot be empty");
        }
        if (roomType == null || roomType.trim().isEmpty()) {
            throw new InvalidBookingException("Room type cannot be empty");
        }
        if (!inventory.roomExists(roomType)) {
            throw new InvalidBookingException("Invalid room type selected");
        }
        if (inventory.getAvailability(roomType) <= 0) {
            throw new InvalidBookingException("Requested room type is not available");
        }
    }
}

public class BookMyStayApp {
    public static void main(String[] args) {
        RoomInventory inventory = new RoomInventory();
        inventory.registerRoom("Standard Room", 2);
        inventory.registerRoom("Deluxe Room", 0);

        InvalidBookingValidator validator = new InvalidBookingValidator();

        String guestName = "Arun";
        String roomType = "Deluxe Room";

        try {
            validator.validate(guestName, roomType, inventory);
            System.out.println("Booking input is valid");
        } catch (InvalidBookingException e) {
            System.out.println("Booking Failed: " + e.getMessage());
        }

        System.out.println("System continues running safely");
    }
}