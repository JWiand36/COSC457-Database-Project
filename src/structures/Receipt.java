package structures;

import java.util.ArrayList;

public class Receipt implements DataInterface{

    private int id;
    private String customer_name;
    private String store_location;
    private double total_balance;
    private ArrayList<Product> products;
    private String date;

    public Receipt(){
        this.id = 0;
        this.customer_name = "";
        this.store_location = "";
        this.total_balance = 0;
        this.products = new ArrayList<>();
        this.date = "";
    }

    public Receipt(String customer_name, String store_location, ArrayList<Product> products, String date) {
        this.customer_name = customer_name;
        this.store_location = store_location;

        for(Product product: products)
            this.total_balance += product.getPrice();

        this.products = products;
        this.date = date;
    }

    public Receipt(int id, String customer_name, String store_location, double total_balance, ArrayList<Product> products, String date){
        this.id = id;
        this.customer_name = customer_name;
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

    public String getStore_location() { return store_location; }

    public double getTotal_balance() {
        return total_balance;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public String getDate() {
        return date;
    }
}
