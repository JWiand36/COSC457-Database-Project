package structures;

public class Employee implements DataInterface{

    private int ssn;
    private int super_ssn;
    private String first_name;
    private String last_name;
    private String address;
    private String sex;
    private int age;
    private int store;

    public Employee(){
        this.ssn = 0;
        this.super_ssn = 0;
        this.first_name = "";
        this.last_name = "";
        this.address = "";
        this.sex = "";
        this.age = 0;
        this.store = 0;
    }

    public Employee(int ssn, int super_ssn, String first_name, String last_name, String address, String sex, int age, int store) {
        this.ssn = ssn;
        this.super_ssn = super_ssn;
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
        this.sex = sex;
        this.age = age;
        this.store = store;
    }

    public int getSsn() {
        return ssn;
    }

    public void setSsn(int ssn) {
        this.ssn = ssn;
    }

    public int getSuper_ssn() {
        return super_ssn;
    }

    public void setSuper_ssn(int super_ssn) {
        this.super_ssn = super_ssn;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getStore() {
        return store;
    }

    public void setStore(int store) {
        this.store = store;
    }
}
