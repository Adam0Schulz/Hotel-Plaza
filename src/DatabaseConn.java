import java.io.*;
import java.time.LocalDate;

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

        hotel.addRoom(new Room(2499, 1001, "Suite", true));
        hotel.addRoom(new Room(2499, 2001, "Suite", true));
        hotel.addRoom(new Room(2499, 3002, "Suite", true));
        hotel.addRoom(new Room(2499, 2002, "Suite", true));
        hotel.addRoom(new Room(549, 1002, "Single-bed", false));
        hotel.addRoom(new Room(549, 2003, "Single-bed", false));
        hotel.addRoom(new Room(599, 1003, "Single-bed", true));
        hotel.addRoom(new Room(599, 2004, "Single-bed", true));
        hotel.addRoom(new Room(599, 1004, "Single-bed", true));
        hotel.addRoom(new Room(749, 2005, "Double-bed", false));
        hotel.addRoom(new Room(749, 1005, "Double-bed", false));
        hotel.addRoom(new Room(799, 2006, "Double-bed", true));
        hotel.addRoom(new Room(799, 1006, "Double-bed", true));
        hotel.addRoom(new Room(799, 2007, "Double-bed", true));
        hotel.addRoom(new Room(799, 1007, "Double-bed", true));
        hotel.addRoom(new Room(799, 2008, "Double-bed", true));

        hotel.addEmployee(new Director("Babak Asgari", 28525011, 75000));
        hotel.addEmployee(new Receptionist("Simona Kardel", 22819043, 30000));
        hotel.addEmployee(new Receptionist("Joanna Meyers", 22819043, 30000));
        hotel.addEmployee(new Receptionist("Søren Thompson", 22819043, 30000));
        hotel.addEmployee(new Accountant("Jessica White", 22558866, 50000));
        hotel.addEmployee(new CleaningPersonnel("Adam Schulz", 33557799, 20000));
        hotel.addEmployee(new CleaningPersonnel("Stine Petersen", 33577799, 20000));
        hotel.addEmployee(new CleaningPersonnel("Steen Jeppesen", 33577799, 20000));
        hotel.addEmployee(new CleaningPersonnel("Maria Mendoza", 37579899, 20000));

        LocalDate today = LocalDate.now();

        hotel.addBooking(new Booking(today, today.plusDays(3),
                hotel.getRooms().get(1),
                new Guest("Adam Schulz", "Amagerbrogade 135, 2300 København S", 11445567)));
        hotel.addBooking(new Booking(today, today.plusDays(7),
                hotel.getRooms().get(0),
                new Guest("John Tingleff", "Tingvej 25, København S", 88774546)));
        hotel.addBooking(new Booking(today.plusDays(8), today.plusDays(14),
                hotel.getRooms().get(9),
                new Guest("Susan Fitzgerald", "Ebbe Rodes Alle 25, 2500 Valby", 88756546)));
        hotel.addBooking(new Booking(today.plusDays(8), today.plusDays(14),
                hotel.getRooms().get(6),
                new Guest("Ole Hendersen", "Frederiksberg Alle 62, 1610", 88756546)));
        hotel.addBooking(new Booking(today.plusDays(25), today.plusDays(30),
                hotel.getRooms().get(6),
                new Guest("Brigit Nielsen", "Nialsgade, 2300 København S", 88756546)));
        hotel.addBooking(new Booking(today.plusDays(45), today.plusDays(52),
                hotel.getRooms().get(10),
                new Guest("Thomas Sørensen", "Rose Lillevej 19, 2300 København S", 88756546)));
        hotel.addBooking(new Booking(today.plusDays(47), today.plusDays(58),
                hotel.getRooms().get(12),
                new Guest("Nina Johnson", "Holbersgade 22, 1025 København K", 88756546)));
        save(hotel);
    }

}
