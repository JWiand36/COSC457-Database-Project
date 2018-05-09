package structures;

import java.util.ArrayList;

public class Order implements DataInterface {

    private int id;
    private int confirmNumber;
    private double cost;
    private double shipping_Cost;
    private ArrayList<Product> products;
    private String shipping_Address;
    private String shipping_Company;

    public Order(){
        this.id = 0;
        this.confirmNumber = 0;
        this.cost = 0;
        this.shipping_Cost = 0;
        this.products = new ArrayList<>();
        this.shipping_Address = "";
        this.shipping_Company = "";
    }

    public Order(int id, int confirmNumber, double cost, double shipping_Cost, ArrayList<Product> products, String shipping_Address, String shipping_Company) {
        this.id = id;
        this.confirmNumber = confirmNumber;
        this.cost = cost;
        this.shipping_Cost = shipping_Cost;
        this.products = products;
        this.shipping_Address = shipping_Address;
        this.shipping_Company = shipping_Company;
    }

    public Order(double cost, String shipping_Address, ArrayList<Product> products) {
        this.confirmNumber = confirmNumber;
        this.cost = cost;
        this.shipping_Address = shipping_Address;
        this.products = products;
        this.shipping_Cost = 0;
        this.shipping_Company = "";
    }

    public void setShipping_Cost(double shipping_Cost) {
        this.shipping_Cost = shipping_Cost;
    }

    public void setShipping_Company(String shipping_Company) {
        this.shipping_Company = shipping_Company;
    }

    public int getId() {
        return id;
    }

    public int getConfirmNumber() {
        return confirmNumber;
    }

    public double getCost() {
        return cost;
    }

    public double getShipping_Cost() {
        return shipping_Cost;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public String getShipping_Address() {
        return shipping_Address;
    }

    public String getShipping_Company() {
        return shipping_Company;
    }
}
