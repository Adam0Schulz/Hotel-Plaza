import java.util.ArrayList;

public interface HotelInter {

    // ArrayList<Room> rooms = new ArrayList<Room>();
    // ArrayList<Employee> employees = new ArrayList<Employee>();

    void addRoom(Room room);

    void removeRoom(Room room);

    ArrayList<Room> getRooms();

    void addEmployee(Employee emp);

    void removeEmployee(Employee emp);

    ArrayList<Room> getEmloyees();

}
