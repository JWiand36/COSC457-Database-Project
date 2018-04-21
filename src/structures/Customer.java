package structures;

public class Customer implements DataInterface{

    private String first_name;
    private String last_name;
    private String address;
    private String sex;
    private double balance;

    public Customer(){
        this.first_name = "";
        this.last_name = "";
        this.address = "";
        this.sex= "";
        this.balance = 0;
    }

    public Customer(String first_name, String last_name, String address, String sex, String balance){
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
        this.sex = sex;
        this.balance = Double.parseDouble(balance);
    }

    public Customer(String first_name, String last_name, String address, String sex) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
        this.sex = sex;
        this.balance = 0;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getAddress() {
        return address;
    }

    public String getSex() {
        return sex;
    }

    public double getBalance() {
        return balance;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void addBalance(int addition){
        this.balance += addition;
    }

    public void reduceBalance(int difference){
        this.balance -= difference;
    }
}
