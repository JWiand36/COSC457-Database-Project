package display.add;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import services.Controller;

public class AddEmployee implements AddInterface{

    private GridPane pane;
    private Controller controller;

    public AddEmployee(Controller controller){

        this.controller = controller;
        this.pane = new GridPane();

        initAddEmployee();
    }

    @Override
    public Pane display(){
        return pane;
    }

    private void initAddEmployee(){

        pane.add(new Label("First Name"), 0, 0);
        pane.add(new Label("Last Name"), 2, 0);

    }
}
