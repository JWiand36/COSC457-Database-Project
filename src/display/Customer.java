package display;


import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import services.Controller;

public class Customer implements DisplayInterface{

    private GridPane pane;
    private Controller controller;

    public Customer(Controller controller){

        this.pane = new GridPane();
        this.controller = controller;

        this.pane.add(new Text("Customer"),0,0);
    }

    @Override
    public Pane display() {
        return pane;
    }
}
