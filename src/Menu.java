import java.util.Scanner;

public class Menu {

    static final String chooseSentence = "Please choose one of the following options: ";
    static Scanner scanner = new Scanner(System.in);


    public static void directorMenu(){
        System.out.println(chooseSentence + "\n(1)List all employees \n(2) Add employee \n(3) Modify employee \n(4) Remove employee");
        int choice = Integer.parseInt(scanner.nextLine());
        switch(choice){
            case 1:
                hotel.printEmployees(); // new method print employees - loop and print each on a new line with index
                break;
            case 2:
                System.out.println("Please enter position(receptionist / accountant / cleaning personel, first name, last name, phone number separated by comma.");
                String[] employeeToAdd = scanner.nextLine().split(", ", 4);

                hotel.addEmployee(new ??? (employeeToAdd[1], employeeToAdd[2], employeeToAdd[3]));
                break;
            case 3:
                System.out.println("Please enter the last name of employee you would like to modify");
                break;
            case 4:
                System.out.println("Please enter first and last name of employee you would like to remove: ");
                String employeeToRemove = scanner.nextLine();
                break;

        }

    }

    public static void receptionistMenu(){
        System.out.println(chooseSentence + "\n(1) Create booking \n(2) Extend booking)");
        int choice = Integer.parseInt(scanner.nextLine());
        switch (choice){
            case 1:
                //createBooking();
                break;
            case 2:
                // search through bookings based on what?
                break;
        }
        // print receipt
        // see available rooms


    }

    public static void accountantMenu(){
        System.out.println(chooseSentence + "(1) ??? (2)");
        // see past bookings/ revenue
        // view monthly salaries
        // export data to excel

    }

    public static void cleaningPersonelMenu(){
        // see rooms to clean

    }
}
