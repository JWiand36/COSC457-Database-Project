package display;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import services.Controller;

public class Inventory implements DisplayInterface{

    private GridPane pane;
    private Controller controller;

    public Inventory(Controller controller){

        pane = new GridPane();
        this.controller = controller;


        this.pane.add(new Text("Inventory"),0,0);
    }

    @Override
    public Pane display(){
        return pane;
    }
}
