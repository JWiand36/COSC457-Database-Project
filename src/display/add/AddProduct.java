package display.add;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import services.Controller;

public class AddProduct implements AddInterface{

    private GridPane pane;


    public AddProduct(Controller controller){

        this.pane = new GridPane();
    }

    @Override
    public Pane display(){
        return pane;
    }
}
