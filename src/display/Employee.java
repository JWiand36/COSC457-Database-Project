package display;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import services.Controller;

public class Employee implements DisplayInterface{

    private GridPane pane;
    private Controller controller;

    public Employee(Controller controller){

        this.pane = new GridPane();
        this.controller = controller;


        this.pane.add(new Text("Employee"),0,0);

    }

    @Override
    public Pane display(){
        return pane;
    }
}
