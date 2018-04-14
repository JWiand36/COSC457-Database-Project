package structures;

public class Customer implements DataInterface{

    private int id;
    private String first_name;
    private String last_name;
    private String address;
    private String sex;
    private double balance;

    public Customer(){
        this.id = 0;
        this.first_name = "";
        this.last_name = "";
        this.address = "";
        this.sex= "";
        this.balance = 0;
    }

    public Customer(int id, String first_name, String last_name, String address, String sex, String balance){
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
        this.sex = sex;
        this.balance = Double.parseDouble(balance);
    }

    public int getId() {
        return id;
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

    public void setId(int id) {
        this.id = id;
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
