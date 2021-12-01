import java.time.LocalDate;

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

    Guest getGuest();

    // void addGuest(Guest guest);

    // void removeGuest(Guest guest);

}
