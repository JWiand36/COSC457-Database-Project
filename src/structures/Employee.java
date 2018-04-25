package structures;

public class Employee implements DataInterface{

    private int ssn;
    private String super_name;
    private String first_name;
    private String last_name;
    private String address;
    private String sex;
    private String store;
    private int age;

    public Employee(){
        this.ssn = 0;
        this.super_name = "";
        this.first_name = "";
        this.last_name = "";
        this.address = "";
        this.sex = "";
        this.store = "";
        this.age = 0;
    }

    public Employee(String super_name, String first_name, String last_name, String address, String sex, int age, String store) {
        this.super_name = super_name;
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
        this.sex = sex;
        this.age = age;
        this.store = store;
    }

    public Employee(int ssn, String super_name, String first_name, String last_name, String address, String sex, int age, String store) {
        this.ssn = ssn;
        this.super_name = super_name;
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
        this.sex = sex;
        this.age = age;
        this.store = store;
    }

    public void setSSN(String ssn){
        this.ssn = Integer.parseInt(ssn);
    }

    public String getSuper_Name() {
        return super_name;
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

    public int getAge() {
        return age;
    }

    public String getStore() {
        return store;
    }

    public int getSsn(){ return ssn; }
}
