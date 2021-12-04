import java.io.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.awt.Desktop;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.ConsoleHandler;

public class Menu {

    static Hotel hotel = App.getDatabase();

    public static void directorMenu() throws NumberFormatException {
        ArrayList<String> options = new ArrayList<>();
        options.add("List all employees");
        options.add("Add employee");
        options.add("Search for employee by name");
        options.add("Add room");
        options.add("List all rooms");
        options.add("Search for room by number");

        Screen.print("To save changes and log out at anytime enter 'Q'");
        int choice = Screen.choice(options);
        switch (choice) {
            case 1:
                Employee employee = listEmployees();
                modifyEmployee(employee);
                break;
            case 2:
                createEmployee();
                break;
            case 3:
                String name = Screen.enter("name of the employee");
                ArrayList searchedEmployees = hotel.searchForEmployee(name);
                Screen.print("To modify employee please enter the corresponding number");
                hotel.printOptions(searchedEmployees);
                int employeeIndex = Screen.scanInt();
                modifyEmployee((Employee) searchedEmployees.get(employeeIndex - 1));
                break;
            case 4:
                String[] roomToAdd = Screen.enter(
                        "price, room number, room type and true/false for wifi separated by comma and space (e.g. 749, 1002, single-bed, true)").split(", ", 4);
                int newRoomNumber = Integer.parseInt(roomToAdd[1]);
                if(hotel.getRoom(newRoomNumber) != null){
                    Screen.print("Room number " + newRoomNumber + " already exists.");
                    directorMenu();
                } else {
                    double newPrice = Double.parseDouble(roomToAdd[0]);
                    boolean newWifi = Boolean.parseBoolean(roomToAdd[3]);
                    hotel.addRoom(new Room(newPrice, newRoomNumber, roomToAdd[2], newWifi));
                    Screen.print("Room number " + newRoomNumber + " has been added.");
                }
                break;
            case 5:
                Room currentRoom = listRooms();
                modifyRoom(currentRoom);
                break;
            case 6:
                int roomNumber = Screen.enterInt("room number");
                Room searchedRoom = hotel.getRoom(roomNumber);
                if (searchedRoom == null) {
                    Screen.print("No match found");
                    Screen.pause();
                } else {
                    Screen.print("To modify room please enter '1'");
                    Screen.print(searchedRoom);
                    if (Screen.scanInt() == 1) {
                        modifyRoom(searchedRoom);
                    }
                }
                break;
        }
        Screen.clear();
        directorMenu();
    }

    // displays menu for modification of employee and changes selected attributes
    public static void modifyEmployee(Employee employee) {
        ArrayList<String> opt = new ArrayList<>();
        opt.add("Modify name");
        opt.add("Modify phone number");
        opt.add("Modify salary");
        opt.add("Delete " + employee.getName() + " from the database");

        int option = Screen.choice(opt);
        switch (option) {
            case 1:
                Screen.print("Current name: " + employee.getName());
                employee.setName(Screen.enter("new name"));
                Screen.print("Name has been changed.");
                Screen.pause();
                break;
            case 2:
                Screen.print("Current phone number: " + employee.getPhoneNum());
                employee.setPhoneNum(Screen.enterInt("new phone number"));
                Screen.print("Phone number has been changed.");
                Screen.pause();
                break;
            case 3:
                Screen.print("Current salary: " + employee.getSalary());
                employee.setSalary(Screen.enterInt("new salary"));
                Screen.print("Salary has been changed.");
                Screen.pause();
                break;
            case 4:
                hotel.removeEmployee(employee);
                Screen.print(employee.getName() + " has been deleted.");
                Screen.pause();
                break;
        }
        Screen.clear();
        directorMenu();
    }

    // prints all employees and returns selected employee
    public static Employee listEmployees() {
        Screen.print("To modify employee please enter the corresponding number");
        hotel.printOptions(hotel.getEmployees());
        int employeeIndex = Screen.scanInt();
        return (Employee) hotel.getEmployees().get(employeeIndex - 1);
    }

    // displays menu for adding employee to the database
    public static void createEmployee() {
        ArrayList<String> choices = new ArrayList<>();
        choices.add("Accountant");
        choices.add("Receptionist");
        choices.add("Cleaning Personel");

        int input = Screen.choice(choices);
        String[] employeeToAdd = Screen.enter("full name, phoneNum and salary separated by comma and space").split(", ",
                3);
        int newNum = Integer.parseInt(employeeToAdd[1]);
        int newSalary = Integer.parseInt(employeeToAdd[2]);
        switch (input) {
            case 1:
                hotel.addEmployee(new Accountant(employeeToAdd[0], newNum, newSalary));
                Screen.print(employeeToAdd[0] + " has been added to the database.");
                break;
            case 2:
                hotel.addEmployee(new Receptionist(employeeToAdd[0], newNum, newSalary));
                Screen.print(employeeToAdd[0] + " has been added to the database.");
                break;
            case 3:
                hotel.addEmployee(new CleaningPersonel(employeeToAdd[0], newNum, newSalary));
                Screen.print(employeeToAdd[0] + " has been added to the database.");
                break;
        }
    }

    // displays menu to modify room
    public static void modifyRoom(Room currentRoom) {
        ArrayList<String> attributes = new ArrayList<>();
        attributes.add("Modify price");
        attributes.add("Modify room number");
        attributes.add("Modify type");
        attributes.add("Modify wifi");
        attributes.add("Delete room number " + currentRoom.getRoomNum() + " from the database");

        int attribute = Screen.choice(attributes);

        switch (attribute) {
            case 1:
                Screen.print("Current price: " + currentRoom.getPrice());
                currentRoom.setPrice(Screen.enterInt("new price")); // maybe we need scanner for double
                Screen.print("Price has been changed.");
                Screen.pause();
                break;
            case 2:
                Screen.print("Current room number: " + currentRoom.getRoomNum());
                currentRoom.setRoomNum(Screen.enterInt("new room number"));
                Screen.print("Room number has been changed.");
                Screen.pause();
                break;
            case 3:
                Screen.print("Current type: " + currentRoom.getType());
                currentRoom.setType(Screen.enter("new type"));
                Screen.print("Type has been changed.");
                Screen.pause();
                break;
            case 4:
                Screen.print("Current wifi status: " + currentRoom.isWifi());
                boolean isWifi = Boolean.parseBoolean(Screen.enter("new wifi status"));
                currentRoom.setWifi(isWifi);
                Screen.print("Wifi status has been changed.");
                Screen.pause();
                break;
            case 5:
                hotel.removeRoom(currentRoom);
                Screen.print("Room number " + currentRoom.getRoomNum() + " has been deleted.");
                Screen.pause();
                break;
        }
        Screen.clear();
        directorMenu();
    }

    // prints all rooms and returns selected room
    public static Room listRooms() {
        Screen.print("To modify room please enter the corresponding number");
        hotel.printOptions(hotel.getRooms());
        return hotel.getRooms().get(Screen.scanInt() - 1);
    }

    public static void receptionistMenu() {
        // Creates options
        ArrayList<String> options = new ArrayList<String>();
        options.add("Create a booking");
        options.add("Modify a booking");

        // Screen.print(hotel.getBookings().size() + ", " + hotel.getBookings().get(0).getGuest().getName());
        // Displays options and stores the choice input
        int choice = Screen.choice(options);
        switch (choice) {
            case 1:
                // Screen.print("Available rooms");
                Booking newBooking = null;
                ArrayList<String> subOptions = new ArrayList<String>();
                subOptions.add("Single-bed");
                subOptions.add("Double-bed");
                subOptions.add("Suite");

                String roomType = (String) Screen.chooseListItem(subOptions);
                LocalDate startDate = null;
                LocalDate endDate = null;
                try {
                    startDate = LocalDate.parse(Screen.enter("start date of the booking (yyyy-mm-dd): "));
                    endDate = LocalDate.parse(Screen.enter("end date of the booking (yyyy-mm-dd): "));
                } catch (Exception e) {
                    Screen.error("Incorrect date format");
                    receptionistMenu();
                }

                long numOfDays = ChronoUnit.DAYS.between(startDate, endDate);

                Screen.print("Available rooms");
                ArrayList<Integer> subOptions2 = new ArrayList<Integer>();
                LocalDate today = LocalDate.now();
                for (Room room : hotel.getRooms()) {
                    if (!(room.isOccupied(startDate, numOfDays)) && room.getType().equals(roomType)) {
                        subOptions2.add(room.getRoomNum());
                    }
                }
                int roomNum = (int) Screen.chooseListItem(subOptions2);
                Guest guest = new Guest(Screen.enter("guest's full name"), Screen.enter("guest's address"),
                        Screen.enterInt("guest's phone number"));

                newBooking = new Booking(startDate, endDate, hotel.getRoom(roomNum), guest);
                hotel.addBooking(newBooking);

                if (hotel.getBookings().get(hotel.getBookings().size() - 1).getRoomNum() == newBooking.getRoomNum()) {
                    Screen.print("Booking has been made successfully");

                }

                String printRec = Screen.enter("Y if you want to print receipt and N if not");
                if (printRec.equalsIgnoreCase("Y")) {
                    newBooking.printReceipt();
                    receptionistMenu();
                } else if (printRec.equalsIgnoreCase("N")) {
                    receptionistMenu();
                }

                break;
            case 2:

                // modify a booking
                String bookName = Screen.enter("name for which was the booking made");
                ArrayList<Booking> bookings = new ArrayList<Booking>();
                for (Booking booking : hotel.getBookings()) {
                    if (booking.getGuest().getName().equalsIgnoreCase(bookName)) {
                        bookings.add(booking);
                    }
                }

                Booking result = (Booking) Screen.chooseListItem(bookings);

                ArrayList<String> sOptions = new ArrayList<String>();
                sOptions.add("Start date");
                sOptions.add("End date");
                sOptions.add("Guest information");
                sOptions.add("Room number");

                int subChoice = Screen.choice(sOptions);
                int index = hotel.getBookings().indexOf(result);
                switch (subChoice) {
                    case 1:
                        Screen.print("Previous date: " + result.getStartDate());
                        LocalDate nStartDate = LocalDate.parse(Screen.enter("new start date of the booking"));
                        result.setStartDate(nStartDate);
                        hotel.updateBooking(index, result);
                        Screen.print("Start date successfully changed");
                        break;
                    case 2:
                        Screen.print("Previous date: " + result.getEndDate());
                        LocalDate nEndDate = LocalDate.parse(Screen.enter("new end date of the booking"));
                        result.setEndDate(nEndDate);
                        hotel.updateBooking(index, result);
                        Screen.print("End date successfully changed");
                        break;
                    case 3:
                        Screen.print("Guest's attributes:\n1: name\n2: address\n3: phone number/phone");
                        String nGuestInfo = Screen.enter(
                                "The attribute that you want to change and the value separated by ' - ' (examp. name - John Newman)");
                        String[] nGuestArray = nGuestInfo.split(" - ");
                        if (nGuestArray[0].equalsIgnoreCase("name")) {
                            Screen.print("Previous name: " + result.getGuest().getName());
                            result.getGuest().setName(nGuestArray[1]);
                            hotel.updateBooking(index, result);
                            Screen.print("New name: " + result.getGuest().getName());
                            Screen.pause();
                            receptionistMenu();
                        } else if (nGuestArray[0].equalsIgnoreCase("address")) {
                            Screen.print("Previous address: " + result.getGuest().getAddress());
                            result.getGuest().setAddress(nGuestArray[1]);
                            hotel.updateBooking(index, result);
                            Screen.print("New address: " + result.getGuest().getAddress());
                            Screen.pause();
                            receptionistMenu();
                        } else if (nGuestArray[0].equalsIgnoreCase("phone number")
                                || nGuestArray[0].equalsIgnoreCase("phone")) {
                            try {
                                Screen.print("Previous phone number: " + result.getGuest().getPhoneNum());
                                result.getGuest().setPhoneNum(Integer.parseInt(nGuestArray[1]));
                                hotel.updateBooking(index, result);
                                Screen.print("New address: " + result.getGuest().getAddress());
                                Screen.pause();
                                receptionistMenu();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        } else {
                            Screen.error("Incorrect attribute");
                            receptionistMenu();
                        }
                        break;
                    case 4:
                        Screen.print("Previous room number: " + result.getRoomNum());
                        int nRoomNum = Screen.enterInt("New room number of the booking: ");
                        if (hotel.getRoom(nRoomNum) == null) {
                            if (!hotel.getRoom(nRoomNum).isOccupied(result.getStartDate(),
                                    result.getNumOfNights())) {
                                result.setRoom(hotel.getRoom(nRoomNum));
                                Screen.print("Room number successfully changed");
                            } else {
                                Screen.error("This room is not available");
                                receptionistMenu();
                            }

                        } else {
                            Screen.error("The room doesn't exist");
                        }
                        break;
                }
                break;

        }
        receptionistMenu();
    }

    public static void accountantMenu() {
        // Creates options for attributes that can be updated
        ArrayList<String> options = new ArrayList<String>();
        options.add("View history of bookings");
        options.add("View employees table");

        // Displays options and stores the choice input
        int choice = Screen.choice(options);

        try {
            FileWriter writer = new FileWriter("accounting_stuff.csv");

            switch (choice) {
                case 1:
                    ArrayList<Booking> bookings = hotel.getBookings();

                    writer.write("Booking start date;Booking end date;Number of nights;Price per night (dkk); Total price (dkk);\n");
                    for (Booking booking : bookings) {
                        writer.write(
                                booking.getStartDate() + ";" + booking.getEndDate() + ";" + booking.getNumOfNights() +";"+ booking.getPrice() + ";" + booking.getPrice() * booking.getNumOfNights() + "\n");
                    }
                    break;
                case 2:
                    ArrayList<Employee> employees = hotel.getEmployees();

                    writer.write("Employee's name;Position;Monthly salary (dkk)\n");
                    for (Employee employee : employees) {
                        writer.write(employee.getName() + ";" + employee.getTitle() + ";" + employee.getSalary() + "\n");
                    }
                    break;
            }

            writer.close();
            System.out.println("Successfully wrote to the file.");
            File file = new File("accounting_stuff.csv");
            Desktop desktop = Desktop.getDesktop();
            desktop.open(file);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public static void cleaningPersonelMenu() {
        Screen.print("To mark room as cleaned please enter the corresponding number");
        hotel.roomsToClean();
        Screen.pause();
        Screen.clear();
        cleaningPersonelMenu();
    }
}
