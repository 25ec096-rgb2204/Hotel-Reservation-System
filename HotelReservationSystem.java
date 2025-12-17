import java.util.ArrayList;
import java.util.Scanner;

public class HotelReservationSystem {
    static ArrayList<Room> rooms = new ArrayList<>();
    static ArrayList<Booking> bookings = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        rooms.add(new Room(101, "Standard"));
        rooms.add(new Room(102, "Deluxe"));
        rooms.add(new Room(103, "Suite"));

        while (true) {
            System.out.println("\n1. View Rooms");
            System.out.println("2. Book Room");
            System.out.println("3. Cancel Booking");
            System.out.println("4. Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    viewRooms();
                    break;
                case 2:
                    bookRoom(sc);
                    break;
                case 3:
                    cancelBooking(sc);
                    break;
                case 4:
                    System.out.println("Thank you!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    static void viewRooms() {
        for (Room r : rooms) {
            System.out.println("Room " + r.roomNumber +
                    " | " + r.type +
                    " | Available: " + r.isAvailable);
        }
    }

    static void bookRoom(Scanner sc) {
        System.out.print("Enter your name: ");
        String name = sc.next();
        System.out.print("Enter room number: ");
        int roomNo = sc.nextInt();

        for (Room r : rooms) {
            if (r.roomNumber == roomNo && r.isAvailable) {
                r.isAvailable = false;
                bookings.add(new Booking(name, r));
                System.out.println("Payment Successful. Room Booked!");
                return;
            }
        }
        System.out.println("Room not available");
    }

    static void cancelBooking(Scanner sc) {
        System.out.print("Enter your name: ");
        String name = sc.next();

        for (Booking b : bookings) {
            if (b.customerName.equals(name)) {
                b.room.isAvailable = true;
                bookings.remove(b);
                System.out.println("Booking Cancelled");
                return;
            }
        }
        System.out.println("No booking found");
    }
}
