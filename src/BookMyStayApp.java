import java.util.*;

class AddOnService {
    String name;
    double price;

    AddOnService(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

class AddOnServiceManager {
    private Map<String, List<AddOnService>> reservationServices = new HashMap<>();

    void addService(String reservationId, AddOnService service) {
        reservationServices.computeIfAbsent(reservationId, k -> new ArrayList<>()).add(service);
    }

    double calculateTotalCost(String reservationId) {
        double total = 0;
        List<AddOnService> services = reservationServices.getOrDefault(reservationId, new ArrayList<>());
        for (AddOnService s : services) {
            total += s.price;
        }
        return total;
    }

    void displayServices(String reservationId) {
        List<AddOnService> services = reservationServices.getOrDefault(reservationId, new ArrayList<>());
        System.out.println("Reservation ID: " + reservationId);
        for (AddOnService s : services) {
            System.out.println("Service: " + s.name + " | Price: " + s.price);
        }
        System.out.println("Total Add-On Cost: " + calculateTotalCost(reservationId));
    }
}

public class BookMyStayApp {
    public static void main(String[] args) {
        AddOnService wifi = new AddOnService("WiFi", 200);
        AddOnService breakfast = new AddOnService("Breakfast", 500);
        AddOnService airportPickup = new AddOnService("Airport Pickup", 800);

        AddOnServiceManager manager = new AddOnServiceManager();

        String reservationId = "ROOM-1";

        manager.addService(reservationId, wifi);
        manager.addService(reservationId, breakfast);
        manager.addService(reservationId, airportPickup);

        manager.displayServices(reservationId);
    }
}