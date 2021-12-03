import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

public class Hotel implements Serializable {

    private ArrayList<Room> rooms = new ArrayList<Room>();
    private ArrayList employees = new ArrayList();
    private ArrayList<Booking> allBookings = new ArrayList<Booking>();

    public void addRoom(Room room) {
        if (rooms.contains(room)) {
            rooms.set(rooms.indexOf(room), room);
        } else {
            rooms.add(room);
        }
    }

    public void removeRoom(Room room) {
        rooms.remove(room);
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void addEmployee(Object emp) {
        if (emp instanceof Director || emp instanceof Accountant || emp instanceof Receptionist
                || emp instanceof CleaningPersonel) {
            employees.add(emp);
        }
    }

    public void removeEmployee(Employee emp) {
        employees.remove(emp);
    }

    public ArrayList getEmployees() {
        return employees;
    }

    public void addBooking(Booking booking) {
        allBookings.add(booking);
        Room room = getRoom(booking.getRoomNum());
        room.addOccupied(booking.getStartDate(), booking.getNumOfNights());
        ;
        addRoom(room);
    }

    public ArrayList<Booking> getBookings() {
        return allBookings;
    }

    public Room getRoom(int roomNum) {
        for (Room room : rooms) {
            if (room.getRoomNum() == roomNum) {
                return room;
            }
        }
        return null;
    }

    public void printOptions(ArrayList arrayList) {
        if (arrayList == null) {
            System.out.println("This list is empty.");
        } else {
            for (int i = 1; i <= arrayList.size(); i++) {
                System.out.println(i + " " + arrayList.get(i - 1));
            }
        }
    }

    public ArrayList searchForEmployee(String name) {
        ArrayList array = new ArrayList();
        for (Object emp : employees) {
            Employee employee = (Employee) emp;
            if (employee.getName().toLowerCase().contains(name.toLowerCase())) {
                array.add(employee);
            }
        }
        return array;
    }

    public void updateBooking(int index, Booking booking) {
        allBookings.set(index, booking);
    }

    public void roomsToClean() {
        Comparator<Booking> byDate = (b1, b2) -> {
            if (b1.getEndDate().isBefore(b2.getEndDate())) {
                return -1;
            } else if (b1.getEndDate().isEqual(b2.getEndDate())) {
                return 0;
            } else {
                return 1;
            }
        };

        allBookings.sort(byDate);

        for (int i = 1; i <= allBookings.size(); i++) {
            String formattedDate = allBookings.get(i - 1).getEndDate()
                    .format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
            System.out.println(i + ":   Room number: " + allBookings.get(i - 1).getRoomNum() + "   Scheduled cleaning: "
                    + formattedDate + "   Status: " + allBookings.get(i - 1).getStatus());
        }
        int cleanedRoom = Screen.scanInt() - 1;
        allBookings.get(cleanedRoom).setStatus("cleaned");
        Screen.print("Room number " + allBookings.get(cleanedRoom).getRoomNum() + " was marked as cleaned.");
    }

}
