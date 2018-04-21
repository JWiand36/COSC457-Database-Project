package structures;

public class Customer implements DataInterface{

    private int id;
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

    public Customer(int id, String first_name, String last_name, String address, String sex, String balance){
        this.id = id;
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

    public int getId(){ return id; }

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
}
