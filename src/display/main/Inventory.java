package display.main;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import structures.Product;

public class Inventory implements DisplayInterface {

    private GridPane pane;

    private Text name = new Text();
    private Text description = new Text();
    private Text amt = new Text();
    private Text price = new Text();

    public Inventory(){

        pane = new GridPane();

        this.pane.add(new Label("Product Name: "), 0 , 0);
        this.pane.add(new Label("Category: "), 0 , 1);
        this.pane.add(new Label("In Stock: "), 0 , 2);
        this.pane.add(new Label("Price: "), 0 , 3);

        this.pane.add(name,1,0);
        this.pane.add(description,1,1);
        this.pane.add(amt,1,2);
        this.pane.add(price,1,3);
    }

    public void displayOne(Product product){

        this.name.setText(product.getName());
        this.description.setText(product.getDescription());
        this.amt.setText(product.getAmt_left() + "");
        this.price.setText(product.getPrice() + "");
    }

    @Override
    public Pane display(){
        return pane;
    }
}
