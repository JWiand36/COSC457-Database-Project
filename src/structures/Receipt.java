package structures;

import java.util.Date;

public class Receipt implements DataInterface{

    private int id;
    private String customer_name;
    private String employee_name;
    private String store_location;
    private double total_balance;
    private Product[] products;
    private Date date;

    public Receipt(){
        this.id = 0;
        this.customer_name = "";
        this.employee_name = "";
        this.store_location = "";
        this.total_balance = 0;
        this.products = new Product[0];
        this.date = new Date();
    }

    public Receipt(int id, String customer_name, String employee_name, String store_location, double total_balance, Product[] products, Date date){
        this.id = id;
        this.customer_name = customer_name;
        this.employee_name = employee_name;
        this.store_location = store_location;
        this.total_balance = total_balance;
        this. products = products;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public String getStore_location() {
        return store_location;
    }

    public void setStore_location(String store_location) {
        this.store_location = store_location;
    }

    public double getTotal_balance() {
        return total_balance;
    }

    public void setTotal_balance(double total_balance) {
        this.total_balance = total_balance;
    }

    public Product[] getProducts() {
        return products;
    }

    public void setProducts(Product[] products) {
        this.products = products;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
