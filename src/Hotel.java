import java.io.Serializable;
import java.util.ArrayList;

public class Hotel implements Serializable {

    private ArrayList<Room> rooms = new ArrayList<Room>();
    private ArrayList employees = new ArrayList();

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public void removeRoom(Room room) {
        rooms.remove(room);
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void addEmployee(Employee emp) {
        employees.add(emp);
    }

    public void removeEmployee(Employee emp) {
        employees.remove(emp);
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }
}
