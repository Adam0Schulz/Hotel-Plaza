import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Room implements Serializable {

    private double price;
    private int roomNum;
    private String type;
    private ArrayList<LocalDate> occupied = new ArrayList<LocalDate>();
    private boolean wifi;

    // Constructor
    public Room(double price, int roomNum, String type, boolean wifi) {
        this.setPrice(price);
        this.setRoomNum(roomNum);
        this.setType(type);
        this.setWifi(wifi);

    }

    // Getters and Setters
    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void changePrice(double price) {
        this.price = price;
    }

    public int getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(int roomNum) {
        this.roomNum = roomNum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<LocalDate> getOccupied() {
        return occupied;
    }

    public void addOccupied(LocalDate StartDate, long numOfDays) {
        occupied.add(StartDate);
        for (int i = 1; i <= numOfDays; i++) {
            occupied.add(StartDate.plusDays(i));
        }
    }

    public boolean isOccupied(LocalDate startDate, long numOfDays) {
        boolean isOccupied = false;
        for (int i = 0; i < numOfDays; i++) {
            if (occupied.contains(startDate.plusDays(i))) {
                isOccupied = true;
            } else {
                isOccupied = false;
            }
        }
        return isOccupied;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public String toString() {
        return Screen.GREEN + "Room number: " + Screen.RESET + roomNum + Screen.GREEN + " Price: " + Screen.RESET + price + Screen.GREEN + " Type: " + Screen.RESET + type + Screen.GREEN + " Wifi: " + Screen.RESET + wifi;
    }
}
