package display.main;

import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import structures.Product;
import structures.Receipt;


public class Sale implements DisplayInterface {

    private GridPane pane;

    private Text location = new Text();
    private Text date = new Text();
    private Text customer = new Text();
    private Text product = new Text();
    private Text total = new Text();

    public Sale(){

        pane = new GridPane();

        this.pane.add(new Label("Location: "), 0 ,0);
        this.pane.add(new Label("Date: "), 0 ,1);
        this.pane.add(new Label("Customer: "), 0 ,2);
        this.pane.add(new Label("Items Purchased: "), 0 ,3);
        this.pane.add(new Label("Total Cost: "), 0 ,4);

        this.pane.add(location,1,0);
        this.pane.add(date,1,1);
        this.pane.add(customer,1,2);
        this.pane.add(product,1,3);
        this.pane.add(total,1,4);

        pane.getColumnConstraints().add(new ColumnConstraints(200));
        pane.setHgap(5);
        pane.setVgap(5);
    }

    public void displayOne(Receipt receipt){

        String temp = "";

        this.location.setText(receipt.getStore_location());
        this.date.setText(receipt.getDate());
        this.customer.setText(receipt.getCustomer_name());

        for(Product product: receipt.getProducts()){
            temp += product.toString() + "\n";
        }

        this.product.setText(temp);
        this.total.setText(receipt.getTotal_balance()+"");
    }

    public Pane display(){
        return pane;
    }
}
