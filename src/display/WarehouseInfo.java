package display;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class WarehouseInfo {

    private VBox pane;

    public WarehouseInfo(){
        pane = new VBox();
    }

    public void setData(ArrayList<String> items){
        pane.getChildren().clear();
        for(String item: items){
            pane.getChildren().add(new Text(item));
        }
    }

    public VBox display(){ return pane; }
}
