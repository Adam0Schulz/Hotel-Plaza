abstract class Employee implements EmployeeInter {

    private String title;
    private String name;
    private int phoneNum;
    private double salary;

    public Employee(){

    }

    public Employee(String title, String name, int phoneNum, double salary) {
        this.setTitle(title);
        this.setName(name);
        this.setPhoneNum(phoneNum);
        this.setSalary(salary);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String newTitle) {
        this.title = newTitle;
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

    public void setSalary(double newSalary) {
        this.salary = newSalary;
    }

    public String toString(){
        return "Title: " + title + ", name: " + name;
    }
}
