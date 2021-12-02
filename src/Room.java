import java.io.Serializable;

public class Room implements Serializable {

    private double price;
    private int roomNum;
    private String type;
    private boolean isAvailable = true;
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

    public boolean IsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean available) {
        isAvailable = available;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public String toString() {
        return "Room number: " + roomNum + " Price: " + price + " Type: " + type;
    }
}
