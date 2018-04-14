package display;


import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import services.Controller;

public class SideBar {

    private HBox pane;
    private Controller controller;

    public SideBar(Controller controller){

        this.pane = new HBox(5);
        this.controller = controller;


        this.pane.getChildren().add(new Text("Side"));
    }

    public Pane display(){
        return pane;
    }
}
