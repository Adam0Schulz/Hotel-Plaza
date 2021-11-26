import java.util.ArrayList;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Booking implements BookingInter {

    // example of how to get local date
    // startDate = LocalDate.of(2021, Month.JANUARY, 1);
    // endDate = LocalDate.of(2021, Month.JANUARY, 5);

    private LocalDate startDate;
    private LocalDate endDate;
    private long numOfNights;
    private ArrayList<Guest> guests;

    public Booking(LocalDate startDate, LocalDate endDate) {
        this.setStartDate(startDate);
        this.setEndDate(endDate);
        this.numOfNights = calcNumOfNights(startDate, endDate);
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

    public ArrayList<Guest> getGuests() {
        return guests;
    }

    public void addGuest(Guest guest) {
        guests.add(guest);
    }

    public void removeGuest(Guest guest) {
        guests.remove(guest);
    }

    public String toString() {
        return startDate + " " + endDate + " " + numOfNights + " " + guests;
    }

}
