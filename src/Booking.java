import java.io.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Booking implements BookingInter, Serializable {

    // example of how to get local date
    // startDate = LocalDate.of(2021, Month.JANUARY, 1);
    // endDate = LocalDate.of(2021, Month.JANUARY, 5);

    public static Hotel hotel = App.getDatabase();

    private LocalDate startDate;
    private LocalDate endDate;
    private long numOfNights;
    private Guest guest;
    private Room room;
    private String status;

    public Booking(LocalDate startDate, LocalDate endDate, Room room, Guest guest) {
        this.setStartDate(startDate);
        this.setEndDate(endDate);
        this.numOfNights = calcNumOfNights(startDate, endDate);
        this.room = room;
        this.guest = guest;
        this.status = "to be cleaned";
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public long getNumOfNights() {
        return numOfNights;
    }

    public long calcNumOfNights(LocalDate startDate, LocalDate endDate) {
        return ChronoUnit.DAYS.between(startDate, endDate);
    }

    public Guest getGuest() {
        return guest;
    }

    /*
     * public void addGuest(Guest guest) {
     * guests.add(guest);
     * }
     */

    /*
     * public void removeGuest(Guest guest) {
     * guests.remove(guest);
     * }
     */

    public void setRoom(Room room) {
        this.room = room;
    }

    public int getRoomNum() {
        return room.getRoomNum();
    }

    public void printReceipt() {
        try {
            FileWriter writer = new FileWriter("receipt" + room.getRoomNum() + ".txt");
            writer.write("Hotel Plaza\n");
            writer.write("Address: Bernstorffsgade 4, 1577 København\n");
            writer.write("Phone: 33 14 92 62\n\n");
            writer.write("Room number: " + room.getRoomNum() + "\n");
            writer.write("Start date: " + startDate + "\n");
            writer.write("End date: " + endDate + "\n");
            writer.write("Number of nights: " + numOfNights + "\n");
            writer.write("Guest: " + guest.getName() + " Phone number: " + guest.getPhoneNum() + "\n");
            writer.write("Price per night: " + room.getPrice() + "dkk\n");
            writer.write("Price in total: " + (getPrice() * numOfNights) + "dkk\n");
            writer.close();

            Runtime.getRuntime().exec("python txtToPdf.py");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String toString() {
        return Screen.GREEN + "Start date: " + Screen.RESET + startDate + Screen.GREEN + " End date: " + Screen.RESET + endDate + Screen.GREEN + " Number of nights: " + Screen.RESET + numOfNights + Screen.GREEN + " Guest´s name: " + Screen.RESET + guest;
    }

    public double getPrice(){
       return room.getPrice();
    }

}
