package display.main;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import structures.Event;

public class EventPane implements DisplayInterface {

    private VBox pane = new VBox(5);
    private Text name = new Text("Name: ");
    private Text date = new Text("Date: ");
    private Text host = new Text("Host Name: ");

    public EventPane(){

        pane.getChildren().addAll(name, date, host);

    }

    public void displayOne(Event event) {
        this.name.setText("Name: "+event.getName());
        this.date.setText("Date: "+ event.getDate());
        this.host.setText("Host Name: "+ event.getHost());

    }

    @Override
    public Pane display() {
        return pane;
    }
}
