import java.awt.Color;
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
