import java.io.Serializable;

public class Director extends Employee implements Serializable {

    // The constructor is not needed if we are not adding additional info specific
    // for the director
    public Director(String name, int phoneNum, double salary) {
        super(name, phoneNum, salary);
    }

}
