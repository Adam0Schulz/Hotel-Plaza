import java.io.Serializable;
import java.util.Random;
import java.text.*;

abstract class Employee implements EmployeeInter, Serializable {

    private String name;
    private int phoneNum;
    private int salary;
    private String password;
    private String title;

    public Employee() {

    }

    public Employee(String name, int phoneNum, int salary, String title) {
        this.setName(name);
        this.setPhoneNum(phoneNum);
        this.setSalary(salary);
        createPassword();
        this.setTitle(title);
    }

    private void createPassword() {
        String passwordCharacters = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!#$%&()*+,-.";
        Random random = new Random();
        String password = "";
        for (int i = 0; i < 5; i++) {
            int r = random.nextInt(passwordCharacters.length());
            password += passwordCharacters.charAt(r);
        }
        this.password = password;
        Screen.print("Login credentials:\nName: " + name + "\nPassword: " + password);
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public int getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(int newPhoneNum) {
        this.phoneNum = newPhoneNum;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(int newSalary) {
        this.salary = newSalary;
    }

    public String toString() {
        return Screen.GREEN + "Position: " + Screen.RESET + title + Screen.GREEN + " Name: " + Screen.RESET + name + Screen.GREEN + " Monthly salary: " + Screen.RESET + salary + Screen.GREEN + " Phone number: " + Screen.RESET + phoneNum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
