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
        Screen.print(hotel);
        for (int i = 0; i < hotel.getEmployees().size(); i++) {
            if (hotel.getEmployees().get(i).getName().equalsIgnoreCase(name)) {

                String password = Screen.enter("your password ");
                if (hotel.getEmployees().get(i).getPassword().equals(password)) {
                    currentUser = hotel.getEmployees().get(i);
                    Screen.print(currentUser);
                    mainMenu();
                } else {
                    Screen.error("This password is incorrect");
                    login();
                }

            } else {
                Screen.error("This name doesn't exist in the system");
                login();
            }
        }
        currentUser = null;
    }

    public static void mainMenu() {
        Screen.print("Main menu");
    }

}
