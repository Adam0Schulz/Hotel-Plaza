import java.io.Serializable;
import java.util.ArrayList;

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
        room.setIsAvailable(false);
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

    public void printRooms(){
        for (int i = 1; i < rooms.size(); i++){
            System.out.println(i + " " + rooms.get(i-1));
        }
    }

    public void printEmployees(){
        for (int i = 1; i < employees.size(); i++){
            System.out.println(i + " " + employees.get(i - 1));
        }
    }

}
