import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public abstract class DatabaseConn {

    public static void main(String[] args) {
        delete();
        initialCreation();
    }

    public static void save(Hotel database) {
        try {
            FileOutputStream fileOut = new FileOutputStream("database.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(database);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in database.ser");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static Hotel load(String file) {
        Hotel database = null;
        try {
            FileInputStream filein = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(filein);
            database = (Hotel) in.readObject();
            in.close();
            filein.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
        }
        return database;

    }

    public static void delete() {
        File myObj = new File("database.ser");
        if (myObj.exists() && !myObj.isDirectory()) {
            if (myObj.delete()) {
                Screen.print("Deleted the file: " + myObj.getName());
            } else {
                Screen.print("Failed to delete the file.");
            }
        }
    }

    public static void initialCreation() {
        Hotel hotel = new Hotel();

        hotel.addRoom(new Room(100, 1001, "suit", true));
        hotel.addRoom(new Room(200, 2001, "two-bed", false));

        hotel.addEmployee(new Director("One and only Babak Asgari", 28525011, 1000000));
        hotel.addEmployee(new Receptionist("Simona Kardel", 22819043, 500000));
        hotel.addEmployee(new Accountant("random guy or girl", 22558866, 2000));
        hotel.addEmployee(new CleaningPersonel("Adam Schulz", 33557799, 200));

        LocalDate today = LocalDate.now();
        hotel.addBooking(new Booking(today, today.plusDays(3), hotel.getRooms().get(1).getRoomNum()));
        hotel.addBooking(new Booking(today, today.plusDays(7), hotel.getRooms().get(0).getRoomNum()));

        save(hotel);
    }

}
