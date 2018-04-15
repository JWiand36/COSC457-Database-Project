package display.add;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import services.Controller;

public class AddSale implements AddInterface{
    private GridPane pane;


    public AddSale(Controller controller){

        this.pane = new GridPane();
    }

    @Override
    public Pane display(){
        return pane;
    }
}
