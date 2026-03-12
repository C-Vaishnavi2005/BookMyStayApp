abstract class Room {
    String type;
    double price;
    int availableRooms;

    Room(String type, double price, int availableRooms) {
        this.type = type;
        this.price = price;
        this.availableRooms = availableRooms;
    }

    void display() {
        System.out.println("Room Type: " + type);
        System.out.println("Price: " + price);
        System.out.println("Available Rooms: " + availableRooms);
        System.out.println();
    }
}

class StandardRoom extends Room {
    StandardRoom(int availableRooms) {
        super("Standard Room", 2000, availableRooms);
    }
}

class DeluxeRoom extends Room {
    DeluxeRoom(int availableRooms) {
        super("Deluxe Room", 3500, availableRooms);
    }
}

class SuiteRoom extends Room {
    SuiteRoom(int availableRooms) {
        super("Suite Room", 5000, availableRooms);
    }
}

public class BookMyStayApp {
    public static void main(String[] args) {
        Room r1 = new StandardRoom(10);
        Room r2 = new DeluxeRoom(5);
        Room r3 = new SuiteRoom(2);

        r1.display();
        r2.display();
        r3.display();
    }
}