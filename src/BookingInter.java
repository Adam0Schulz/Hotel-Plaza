import java.sql.Date;
import java.util.ArrayList;

public interface BookingInter {

    // Date startDate;
    // Date endDate;
    // int numOfNights;
    // ArrayList<Guest> guests;

    Date getStartDate();

    void setStartDate(Date newStartDate);

    Date getEndDate();

    void setEndDate(Date newEndDate);

    int getNumOfNights();

    void calcNumOfNights(); // sets the number of nights to calculated amount

    ArrayList<Guest> getGuests();

    void addGuest(Guest guest);

    void removeGuest(Guest guest);

}
