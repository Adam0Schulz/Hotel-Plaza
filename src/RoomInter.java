public interface RoomInter {

    // double price;
    // int roomNum;
    // String type;
    // boolean isAvailable;
    // Booking booking;
    // boolean wifi;

    double getPrice();

    void ChangePrice(double newPrice);

    int getRoomNum();

    String getType();

    boolean getIsAvailable();

    void setIsAvailable(boolean newAvailability);

    boolean isWifi();

}
