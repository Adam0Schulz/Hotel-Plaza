public interface RoomInter {

    // double price;
    // int roomNum;
    // String type;
    // boolean isAvailable;
    // Booking booking;
    // boolean wifi;

    double getPrice();

    void changePrice(double newPrice);

    int getRoomNum();

    String getType();

    boolean IsAvailable();

    void setIsAvailable(boolean newAvailability);

    boolean isWifi();

}
