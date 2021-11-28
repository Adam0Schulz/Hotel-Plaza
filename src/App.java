import java.io.*;
import java.awt.*;
import javax.swing.JFrame;

public class App {

    private static Hotel hotel = DatabaseConn.load("database.ser");
    private static Employee currentUser;

    public static Hotel getDatabase() {
        return hotel;
    }

    public static void main(String[] args) throws Exception {
        // System.out.println("hello");
        // Screen.window();

        login();

    }

    public static void login() {
        String name = Screen.enter("your full name ");
        for (int i = 0; i < hotel.getEmployees().size(); i++) {
            Screen.print(hotel.getEmployees());
            if (((Employee) hotel.getEmployees().get(i)).getName().equalsIgnoreCase(name)) {

                String password = Screen.enter("your password ");
                if (((Employee) hotel.getEmployees().get(i)).getPassword().equals(password)) {
                    if (hotel.getEmployees().get(i) instanceof Director) {
                        currentUser = (Director) hotel.getEmployees().get(i);
                        mainMenu();
                    } else if (hotel.getEmployees().get(i) instanceof Accountant) {
                        currentUser = (Accountant) hotel.getEmployees().get(i);
                        Menu.accountantMenu();
                    } else if (hotel.getEmployees().get(i) instanceof Receptionist) {
                        currentUser = (Receptionist) hotel.getEmployees().get(i);
                        mainMenu();
                    } else if (hotel.getEmployees().get(i) instanceof CleaningPersonel) {
                        currentUser = (CleaningPersonel) hotel.getEmployees().get(i);
                        mainMenu();
                    }

                } else {
                    Screen.error("This password is incorrect");
                    login();
                }

            }
        }
        currentUser = null;
    }

    public static void mainMenu() {
        Screen.print("Main menu");
    }

}
