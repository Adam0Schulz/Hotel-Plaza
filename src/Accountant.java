import java.io.Serializable;

public class Accountant extends Employee implements Serializable {

    public Accountant(String name, int phoneNum, double salary) {
        super(name, phoneNum, salary);
    }

}
