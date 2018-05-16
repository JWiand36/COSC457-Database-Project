package structures;

public class Product implements DataInterface{

    private int id;
    private String name;
    private String description;
    private int amt_left;
    private double price;

    public Product(){
        this.name = "";
        this.description = "";
        this.amt_left = 0;
        this.price = 0;
    }

    public Product(int id, String name, String description, int amt_left, double price){
        this.id = id;
        this.name = name;
        this.description = description;
        this.amt_left = amt_left;
        this.price = price;
    }

    public Product(String name, String description, int amt_left, double price){
        this.name = name;
        this.description = description;
        this.amt_left = amt_left;
        this.price = price;
    }

    public int getId(){ return id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String desciption) {
        this.description = desciption;
    }

    public int getAmt_left() {
        return amt_left;
    }

    public void setAmt_left(int amt_left) {
        this.amt_left = amt_left;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String toString(){
        return this.name + ": $" + this.price;
    }

    public void addDiscount(double discount){
        if(price > discount){
            price -= discount;
            price = (double) Math.round(price * 100) / 100;
        }

        name += "*";
    }
}
