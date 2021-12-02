import java.awt.Color;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

abstract class Screen {

    public static Scanner scanner = new Scanner(System.in);

    // GUI
    public static void window() {

        JFrame frame = new JFrame();

        ImageIcon favicon = new ImageIcon("src/img/favicon.png");
        frame.setIconImage(favicon.getImage());

        ImageIcon mainLogoImage = new ImageIcon("src/img/big_logo.png");
        JLabel mainPage = new JLabel();
        mainPage.setIcon(mainLogoImage);
        mainPage.setHorizontalAlignment(JLabel.CENTER);
        frame.add(mainPage);

        frame.setTitle("Hotel Plaza");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setSize(1500, 1000);
        frame.setVisible(true);
        frame.getContentPane().setBackground(new Color(0x44949B));

    }

    // Enter method - returns your answear after a request
    public static String enter(String info) {
        print("Please enter " + info + ": ");
        return scanStr();
    }

    public static int enterInt(String info) {
        return Integer.parseInt(enter(info));
    }

    // Displays options
    public static void listOptions(ArrayList<String> options) {
        if (options.size() == 0) {
            print("\nThis list is empty\n");
        } else {
            for (int i = 1; i <= options.size(); i++) {
                print(i + ": " + options.get(i - 1));
            }
        }

    }

    // Displays options and returns the choice int
    public static int choice(ArrayList<String> options) {
        print("Please choose one of the following: ");
        listOptions(options);
        int choice = scanInt();
        if (options.size() < choice) {
            error("Incorrect choice");
            pause();
            App.mainMenu();
        }
        return choice;
    }

    // Displays options, selects the choice from the options and returns it
    public static Object chooseListItem(ArrayList array) {

        try {
            Object object = (Object) array.get(0);
        } catch (Exception e) {
            print("\nThis list is empty\n");
            pause();
            return null;
        }

        ArrayList<String> strings = new ArrayList<String>();
        for (Object object : array) {
            strings.add(object.toString());

        }

        if (array.get(0) instanceof Room) {
            Room room;
            int choice = choice(strings);
            room = (Room) array.get(choice - 1);
            return room;

        } else if (array.get(0) instanceof Booking) {
            Booking booking;

            int choice = choice(strings);
            booking = (Booking) array.get(choice - 1);
            return booking;

        } else if (array.get(0) instanceof Guest) {
            Guest guest;

            int choice = choice(strings);
            guest = (Guest) array.get(choice - 1);
            return guest;

        } else if (array.get(0) instanceof Employee) {
            Employee employee;

            int choice = choice(strings);
            employee = (Employee) array.get(choice - 1);
            return employee;

        } else if (array.get(0) instanceof String) {
            String string;

            int choice = choice(strings);
            string = (String) array.get(choice - 1);
            return string;

        } else if (array.get(0) instanceof Integer) {
            int integer;

            int choice = choice(strings);
            integer = (int) array.get(choice - 1);
            return integer;

        } else {
            return null;
        }

    }

    // Basic sout
    public static void print(Object value) {
        System.out.println(value);
    }

    public static String scanStr() {

        String input = scanner.nextLine();

        int intInput = 1;
        try {
            intInput = Integer.parseInt(input);
        } catch (Exception e) {
        }

        if (input.equalsIgnoreCase("q")) {
            print("Saving...");
            DatabaseConn.save(App.getDatabase());
            pause();
            clear();
            System.exit(0);

        } else if (intInput == 0) {
            App.mainMenu();
        }

        return input;
    }

    public static int scanInt() {
        /*
         * The reason why I'm using the nextLine() and then parsing it to integer and
         * not nextInt() is that nextInt() causes problems with leftover \n (enters)
         */
        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("q")) {
            print("Saving...");
            DatabaseConn.save(App.getDatabase());
            pause();
            clear();
            System.exit(0);

        }
        int intInput = 1;
        try {
            intInput = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            print(new Error("Error: expected input type - int"));
            App.mainMenu();
        }
        if (intInput == 0) {
            App.mainMenu();
        }

        return intInput;
    }

    public static void pause() {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Clears the console
    public static void clear() {

        print("\033[H\033[2J");
        System.out.flush();

    }

    public static void error(String input) {
        print(input + ". Please try again.\n");
    }
}
