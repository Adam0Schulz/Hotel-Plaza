import java.io.*;
import java.time.LocalDate;
import java.awt.Desktop;
import java.util.ArrayList;

public class Menu {

    static Hotel hotel = App.getDatabase();

    public static void directorMenu()throws NumberFormatException {
        ArrayList<String> options = new ArrayList<>();
        options.add("List all employees");
        options.add("Add employee");
        options.add("Add room");
        options.add("List all rooms");

        int choice = Screen.choice(options);
        switch(choice){
            case 1:
                Employee employee = listEmployees();
                modifyEmployee(employee);
            case 2:
                createEmployee();
            case 3:
                String[] roomToAdd = Screen.enter("price, room number, room type(single / double / suite) and true/false for wifi as in example below.\n999, 345, double, true").split(", ", 4);
                double newPrice = Double.parseDouble(roomToAdd[0]);
                int newRoomNumber = Integer.parseInt(roomToAdd[1]);
                boolean newWifi = Boolean.parseBoolean(roomToAdd[3]);
                hotel.addRoom(new Room(newPrice, newRoomNumber, roomToAdd[2],newWifi));
                Screen.print("Room number " + newRoomNumber + " has been added.");
                break;
            case 4:
                Room currentRoom = listRooms();
                modifyRoom(currentRoom);
        }
    }

    // displays menu for modification of employee and changes selected attributes (go back method needs to be added)
    public static void modifyEmployee(Employee employee){
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
                break;
            case 2:
                Screen.print("Current phone number: " + employee.getPhoneNum());
                employee.setPhoneNum(Screen.enterInt("new phone number"));
                Screen.print("Phone number has been changed.");
                break;
            case 3:
                Screen.print("Current salary: " + employee.getSalary());
                employee.setSalary(Screen.enterInt("new salary"));
                Screen.print("Salary has been changed.");
                break;
            case 4:
                hotel.removeEmployee(employee);
                Screen.print(employee.getName() + " has been deleted.");
                break;
        }
    }

    // prints all employees and returns selected employee
    public static Employee listEmployees(){
        Screen.print("To modify employee please enter the corresponding number.");
        hotel.printEmployees();
        int employeeIndex = Screen.scanInt();
        return (Employee) hotel.getEmployees().get(employeeIndex - 1);
    }

    // displays menu for adding employee to the database
    public static void createEmployee(){
        ArrayList<String> choices = new ArrayList<>();
        choices.add("Accountant");
        choices.add("Receptionist");
        choices.add("Cleaning Personel");

        int input = Screen.choice(choices);
        String[] employeeToAdd = Screen.enter("full name, phoneNum and salary separated by comma and space").split(", ", 3);
        int newNum = Integer.parseInt(employeeToAdd[1]);
        double newSalary = Double.parseDouble(employeeToAdd[2]);
        switch (input) {
            case 1:
                hotel.addEmployee(new Accountant(employeeToAdd[0], newNum, newSalary));
                Screen.print("Employee has been added to the database.");
                break;
            case 2:
                hotel.addEmployee(new Receptionist(employeeToAdd[0], newNum, newSalary));
                Screen.print("Employee has been added to the database.");
                break;
            case 3:
                hotel.addEmployee(new CleaningPersonel(employeeToAdd[0], newNum, newSalary));
                Screen.print("Employee has been added to the database.");
                break;
        }
    }

    // displays menu to modify room
    public static void modifyRoom(Room currentRoom){
        ArrayList<String> attributes = new ArrayList<>();
        attributes.add("Modify price");
        attributes.add("Modify room number");
        attributes.add("Modify type");
        attributes.add("Modify wifi");
        attributes.add("Delete " + currentRoom.getRoomNum() + " from the database");

        int attribute = Screen.choice(attributes);

        switch (attribute){
            case 1:
                Screen.print("Current price: " + currentRoom.getPrice());
                currentRoom.setPrice(Screen.enterInt("new price")); // maybe we need scanner for double
                Screen.print("Price has been changed.");
                break;
            case 2:
                Screen.print("Current room number: " + currentRoom.getRoomNum());
                currentRoom.setRoomNum(Screen.enterInt("new room number"));
                Screen.print("Room number has been changed.");
                break;
            case 3:
                Screen.print("Current type: " + currentRoom.getType());
                currentRoom.setType(Screen.enter("new type"));
                Screen.print("Type has been changed.");
                break;
            case 4:
                Screen.print("Current wifi status: " + currentRoom.isWifi());
                boolean isWifi = Boolean.parseBoolean(Screen.enter("new wifi status"));
                currentRoom.setWifi(isWifi);
                Screen.print("Wifi status has been changed.");
                break;
            case 5:
                hotel.removeRoom(currentRoom);
                Screen.print("Room number " + currentRoom.getRoomNum() + " has been deleted.");
                break;
        }


    }

    // prints all rooms and returns selected room
    public static Room listRooms(){
        Screen.print("To modify room please enter the corresponding number.");
        hotel.printRooms();
        return hotel.getRooms().get(Screen.scanInt() - 1);
    }


    public static void receptionistMenu() {
        // Creates options
        ArrayList<String> options = new ArrayList<String>();
        options.add("Create a booking");
        options.add("Modify a booking");

        // Displays options and stores the choice input
        int choice = Screen.choice(options);
        switch (choice) {
            case 1:
                Screen.print("Available rooms");
                ArrayList<Integer> subOptions = new ArrayList<Integer>();
                for (Room room : hotel.getRooms()) {
                    if (room.IsAvailable()) {
                        subOptions.add(room.getRoomNum());
                    }
                }

                Booking newBooking = null;
                int roomNum = (int) Screen.chooseListItem(subOptions);
                Guest guest = new Guest(Screen.enter("guest's full name: "), Screen.enter("guest's address: "),
                        Screen.enterInt("guest's phone number: "));
                try {
                    LocalDate startDate = LocalDate.parse(Screen.enter("start date of the booking (yyyy-mm-dd): "));
                    LocalDate endDate = LocalDate.parse(Screen.enter("start date of the booking (yyyy-mm-dd): "));
                    newBooking = new Booking(startDate, endDate, hotel.getRoom(roomNum), guest);
                    hotel.addBooking(newBooking);
                } catch (Exception e) {
                    Screen.error("Incorrect date format inputed");
                    receptionistMenu();
                }

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

                break;

        }
        receptionistMenu();
    }

    public static void accountantMenu() {
        // Creates options for attributes that can be updated
        ArrayList<String> options = new ArrayList<String>();
        options.add("View the booking history table");
        options.add("View the employees table");

        // Displays options and stores the choice input
        int choice = Screen.choice(options);

        try {
            FileWriter writer = new FileWriter("accounting_stuff.csv");

            switch (choice) {
                case 1:
                    ArrayList<Booking> bookings = hotel.getBookings();

                    writer.write("booking start date;booking end date;booking room number\n");
                    for (Booking booking : bookings) {
                        writer.write(
                                booking.getStartDate() + ";" + booking.getEndDate() + ";" + booking.getRoomNum()
                                        + "\n");
                    }
                    break;
                case 2:
                    ArrayList<Employee> employees = hotel.getEmployees();

                    writer.write("Employee's name;position;salary\n");
                    for (Employee employee : employees) {
                        writer.write(employee.getName() + ";;" + employee.getSalary() + "\n");
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
        // see rooms to clean

    }
}
