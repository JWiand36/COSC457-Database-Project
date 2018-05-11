package structures;

public class Company {

    private int id;
    private String name;
    private double cost;

    public Company(int id, String name, double cost){
        this.id = id;
        this.name = name;
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public String getName(){
        return name;
    }

    public double getCost() {
        return cost;
    }
}
