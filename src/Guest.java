import java.io.Serializable;

public class Guest implements GuestInter, Serializable {

    private String name;
    private String address;
    private int phoneNum;

    public Guest(String name, String address, int phoneNum) {
        this.setName(name);
        this.setAddress(address);
        this.setPhoneNum(phoneNum);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhoneNum() {
        return this.phoneNum;
    }

    public void setPhoneNum(int phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String toString(){
        return name;
    }
}
