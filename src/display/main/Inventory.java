package display.main;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import structures.Product;

public class Inventory implements DisplayInterface {

    private GridPane pane;

    private Text name = new Text("Inventory");

    public Inventory(){

        pane = new GridPane();


        this.pane.add(name,0,0);
    }

    public void displayOne(Product product){
        this.name.setText(product.getName());
    }

    @Override
    public Pane display(){
        return pane;
    }
}
