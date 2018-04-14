package display;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import services.Controller;

public class Sale implements DisplayInterface{

    private GridPane pane;
    private Controller controller;

    public Sale(Controller controller){

        this.controller = controller;

        pane = new GridPane();
        this.pane.add(new Text("Sale"),0,0);

    }

    public Pane display(){
        return pane;
    }
}
