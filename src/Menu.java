import java.io.*;
import java.time.LocalDate;
import java.awt.Desktop;
import java.util.ArrayList;

public class Menu {

    static Hotel hotel = App.getDatabase();

    /*
     * public static void directorMenu(){ System.out.println(chooseSentence +
     * "\n(1)List all employees \n(2) Add employee \n(3) Modify employee \n(4) Remove employee"
     * ); int choice = Integer.parseInt(scanner.nextLine()); switch(choice){ case 1:
     * hotel.printEmployees(); // new method print employees - loop and print each
     * on a new line with index break; case 2: System.out.
     * println("Please enter position(receptionist / accountant / cleaning personel, first name, last name, phone number separated by comma."
     * ); String[] employeeToAdd = scanner.nextLine().split(", ", 4);
     * 
     * hotel.addEmployee(new ??? (employeeToAdd[1], employeeToAdd[2],
     * employeeToAdd[3])); break; case 3: System.out.
     * println("Please enter the last name of employee you would like to modify");
     * break; case 4: System.out.
     * println("Please enter first and last name of employee you would like to remove: "
     * ); String employeeToRemove = scanner.nextLine(); break;
     * 
     * }
     * 
     * }
     */

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
                Screen.print(hotel.getRooms().toString());
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
        // print receipt
        // see available rooms

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
