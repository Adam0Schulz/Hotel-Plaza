
public class App {

    private static Hotel hotel = DatabaseConn.load("database.ser");
    private static Employee emp;

    public static Hotel getDatabase() {
        return hotel;
    }

    public static void main(String[] args) throws Exception {
        // System.out.println("hello");
        // Screen.window();

        login();
        // Menu.receptionistMenu();
        // Menu.accountantMenu();
        // Menu.cleaningPersonelMenu();
    }

    public static void login() {
        String name = Screen.enter("your full name");
        for (int i = 0; i < hotel.getEmployees().size(); i++) {
            Screen.print(hotel.getEmployees());
            if (((Employee) hotel.getEmployees().get(i)).getName().equalsIgnoreCase(name)) {

                String password = Screen.enter("your password");
                if (((Employee) hotel.getEmployees().get(i)).getPassword().equals(password)) {
                    emp = (Employee) hotel.getEmployees().get(i);

                } else {
                    Screen.error("This password is incorrect");
                    login();
                }

            }
        }
        emp = null;
    }

    public static void mainMenu() {
        if (emp instanceof Director) {
            Menu.directorMenu();
        } else if (emp instanceof Accountant) {
            Menu.accountantMenu();
        } else if (emp instanceof Receptionist) {
            Menu.receptionistMenu();
        } else if (emp instanceof CleaningPersonel) {
            Menu.cleaningPersonelMenu();
        }
    }
}
