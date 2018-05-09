package structures;

public class Promotion implements DataInterface {

    private int id;
    private String item;
    private double discount;
    private String date;

    public Promotion(){
        this.item = "";
        this.discount = 0;
        this.date = "";
    }

    public Promotion(int id, String item, double discount, String date) {
        this.id = id;
        this.item = item;
        this.discount = discount;
        this.date = date;
    }

    public Promotion(String item, double discount, String date) {
        this.item = item;
        this.discount = discount;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getItem(){ return item; };

    public double getDiscount() {
        return discount;
    }

    public String getDate() {
        return date;
    }
}
