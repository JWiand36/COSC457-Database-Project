package structures;

public class Event implements DataInterface {

    private int id;
    private String name;
    private String date;
    private String host;

    public Event(){
        this.name = "";
        this.date = "";
        this.host = "";
    }

    public Event(int id, String name, String date, String host) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.host = host;
    }

    public Event(String name, String date, String host) {
        this.name = name;
        this.date = date;
        this.host = host;
    }

    public int getId(){ return id;}

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getHost() {
        return host;
    }
}
