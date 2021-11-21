import java.time.LocalDate;
import java.util.Date;
import java.util.ArrayList;

public interface BookingInter {

    // Date startDate;
    // Date endDate;
    // int numOfNights;
    // ArrayList<Guest> guests;

    LocalDate getStartDate();

    void setStartDate(LocalDate newStartDate);

    LocalDate getEndDate();

    void setEndDate(LocalDate newEndDate);

    long getNumOfNights();

    long calcNumOfNights(LocalDate startDate, LocalDate endDate); // sets the number of nights to calculated amount

    ArrayList<Guest> getGuests();

    void addGuest(Guest guest);

    void removeGuest(Guest guest);

}
