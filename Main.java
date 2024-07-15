import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
class Room {
    private int number;
    private String type;
    private double price;
    private boolean available;
    public Room(int number, String type, double price) {
        this.number = number;
        this.type = type;
        this.price = price;
        this.available = true;
    }
    public int getNumber() {
        return number;
    }
    public String getType() {
        return type;
    }
    public double getPrice() {
        return price;
    }
    public boolean isAvailable() {
        return available;
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }
    @Override
    public String toString() {
        return "Room{" +
                "number=" + number +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", available=" + available +
                '}';
    }
}
class Reservation {
    private static int counter = 0;
    private int id;
    private int roomId;
    private String guestName;
    private Date checkInDate;
    private Date checkOutDate;
    private double totalPrice;
    public Reservation(int roomId, String guestName, Date checkInDate, Date checkOutDate, double totalPrice) {
        this.id = ++counter;
        this.roomId = roomId;
        this.guestName = guestName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalPrice = totalPrice;
    }
    public int getId() {
        return id;
    }
    public int getRoomId() {
        return roomId;
    }
    public String getGuestName() {
        return guestName;
    }
    public Date getCheckInDate() {
        return checkInDate;
    }
    public Date getCheckOutDate() {
        return checkOutDate;
    }
    public double getTotalPrice() {
        return totalPrice;
    }
    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", roomId=" + roomId +
                ", guestName='" + guestName + '\'' +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
class HotelManager {
    private ArrayList<Room> rooms;
    private ArrayList<Reservation> reservations;
    public HotelManager() {
        rooms = new ArrayList<>();
        reservations = new ArrayList<>();
    }
    public void addRoom(Room room) {
        rooms.add(room);
    }
    public ArrayList<Room> checkRoomAvailability(Date checkIn, Date checkOut) {
        ArrayList<Room> availableRooms = new ArrayList<>();
        for (Room room : rooms) {
            boolean isAvailable = true;
            for (Reservation reservation : reservations) {
                if (reservation.getRoomId() == room.getNumber() &&
                        !(checkOut.before(reservation.getCheckInDate()) || checkIn.after(reservation.getCheckOutDate()))) {
                    isAvailable = false;
                    break;
                }
            }
            if (isAvailable) {
                availableRooms.add(room);
            }
        }
        return availableRooms;
    }
    public Reservation makeReservation(int roomId, String guestName, Date checkIn, Date checkOut) throws Exception {
        Room room = getRoomById(roomId);
        if (room == null || !room.isAvailable()) {
            throw new Exception("Room not available");
        }
        double totalPrice = room.getPrice() * (checkOut.getTime() - checkIn.getTime()) / (1000 * 60 * 60 * 24);
        Reservation reservation = new Reservation(roomId, guestName, checkIn, checkOut, totalPrice);
        reservations.add(reservation);
        room.setAvailable(false);
        return reservation;
    }
    public boolean cancelReservation(int reservationId) {
        for (Reservation reservation : reservations) {
            if (reservation.getId() == reservationId) {
                reservations.remove(reservation);
                Room room = getRoomById(reservation.getRoomId());
                if (room != null) {
                    room.setAvailable(true);
                }
                return true;
            }
        }
        return false;
    }
    public boolean checkIn(int reservationId) {
        Reservation reservation = getReservationById(reservationId);
        if (reservation != null) {
            Room room = getRoomById(reservation.getRoomId());
            if (room != null) {
                room.setAvailable(false);
                return true;
            }
        }
        return false;
    }
    public boolean checkOut(int reservationId) {
        Reservation reservation = getReservationById(reservationId);
        if (reservation != null) {
            Room room = getRoomById(reservation.getRoomId());
            if (room != null) {
                room.setAvailable(true);
                reservations.remove(reservation);
                return true;
            }
        }
        return false;
    }
    private Room getRoomById(int roomId) {
        for (Room room : rooms) {
            if (room.getNumber() == roomId) {
                return room;
            }
        }
        return null;
    }
    private Reservation getReservationById(int reservationId) {
        for (Reservation reservation : reservations) {
            if (reservation.getId() == reservationId) {
                return reservation;
            }
        }
        return null;
    }
}
public class Main {
    private static HotelManager hotelManager = new HotelManager();
    public static void main(String[] args) {
        initializeRooms();
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        while (true) {
            System.out.println("1. View Room Availability");
            System.out.println("2. Make Reservation");
            System.out.println("3. Cancel Reservation");
            System.out.println("4. Check-in");
            System.out.println("5. Check-out");
            System.out.println("6. Exit");
            System.out.print("Select an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();
            try {
                switch (option) {
                    case 1:
                        System.out.print("Enter check-in date (dd-MM-yyyy): ");
                        Date checkInDate = dateFormat.parse(scanner.nextLine());
                        System.out.print("Enter check-out date (dd-MM-yyyy): ");
                        Date checkOutDate = dateFormat.parse(scanner.nextLine());
                        ArrayList<Room> availableRooms = hotelManager.checkRoomAvailability(checkInDate, checkOutDate);
                        if (availableRooms.isEmpty()) {
                            System.out.println("No rooms available.");
                        } else {
                            for (Room room : availableRooms) {
                                System.out.println(room);
                            }
                        }
                        break;
                    case 2:
                        System.out.print("Enter room number: ");
                        int roomId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        System.out.print("Enter guest name: ");
                        String guestName = scanner.nextLine();
                        System.out.print("Enter check-in date (dd-MM-yyyy): ");
                        checkInDate = dateFormat.parse(scanner.nextLine());
                        System.out.print("Enter check-out date (dd-MM-yyyy): ");
                        checkOutDate = dateFormat.parse(scanner.nextLine());
                        Reservation reservation = hotelManager.makeReservation(roomId, guestName, checkInDate, checkOutDate);
                        System.out.println("Reservation made: " + reservation);
                        break;
                    case 3:
                        System.out.print("Enter reservation ID to cancel: ");
                        int reservationId = scanner.nextInt();
                        boolean cancelled = hotelManager.cancelReservation(reservationId);
                        if (cancelled) {
                            System.out.println("Reservation cancelled.");
                        } else {
                            System.out.println("Reservation not found.");
                        }
                        break;
                    case 4:
                        System.out.print("Enter reservation ID to check-in: ");
                        reservationId = scanner.nextInt();
                        boolean checkedIn = hotelManager.checkIn(reservationId);
                        if (checkedIn) {
                            System.out.println("Checked in successfully.");
                        } else {
                            System.out.println("Check-in failed.");
                        }
                        break;
                    case 5:
                        System.out.print("Enter reservation ID to check-out: ");
                        reservationId = scanner.nextInt();
                        boolean checkedOut = hotelManager.checkOut(reservationId);
                        if (checkedOut) {
                            System.out.println("Checked out successfully.");
                        } else {
                            System.out.println("Check-out failed.");
                        }
                        break;
                    case 6:
                        System.out.println("Exiting...");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid option. Try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
    private static void initializeRooms() {
        hotelManager.addRoom(new Room(101, "Single", 100.0));
        hotelManager.addRoom(new Room(102, "Double", 150.0));
        hotelManager.addRoom(new Room(103, "Suite", 200.0));
    }
}
