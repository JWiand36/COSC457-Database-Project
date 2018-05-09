package display.add;

import display.main.DisplayInterface;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import services.Controller;
import services.Display;
import structures.Product;

import java.util.ArrayList;

public class AddProduct implements DisplayInterface{

    private GridPane pane;

    private TextField name = new TextField();
    private TextField description = new TextField();
    private TextField amt = new TextField();
    private TextField price = new TextField();

    public AddProduct(Controller controller, Display display){

        this.pane = new GridPane();
        Button warehouse = new Button("Warehouse");
        Button vendor = new Button("Vendor");
        Button submit = new Button("Submit");
        Button add = new Button("Add");
        Button remove = new Button("Reduce");


        pane.add(new Label("Name: "), 0,0);
        pane.add(new Label("Description: "), 2,0);
        pane.add(new Label("Amount: "), 0,1);
        pane.add(new Label("Price: "), 2,1);
        pane.add(name, 1,0);
        pane.add(description,3,0);
        pane.add(amt,1,1);
        pane.add(price,3,1);

        pane.add(add, 0,3);
        pane.add(remove, 1, 3);
        pane.add(warehouse, 2, 3);
        pane.add(vendor, 3, 3);
        pane.add(submit, 0,4);

        pane.getColumnConstraints().add(new ColumnConstraints(200));
        pane.setHgap(5);
        pane.setVgap(5);


        submit.setOnAction(e->{
            Product product = new Product(name.getText(), description.getText(), Integer.parseInt(amt.getText()),
                    Double.parseDouble(price.getText()));

            controller.addData(product, Display.INVENTORY);
        });

        add.setOnAction(e->{

                Product product = new Product(name.getText(), description.getText(), Integer.parseInt(amt.getText()),
                Double.parseDouble(price.getText()));
                controller.addAmt(Integer.parseInt(amt.getText()), product);
        });

        remove.setOnAction(e->{

                Product product = new Product(name.getText(), description.getText(), Integer.parseInt(amt.getText()),
                Double.parseDouble(price.getText()));
                controller.subAmt(Integer.parseInt(amt.getText()), product);
        });

        warehouse.setOnAction(e-> display.displaySideInfo(Display.WARHOUSE));

        vendor.setOnAction(e-> display.displaySideInfo(Display.VENDOR));
    }

    @Override
    public Pane display(){
        return pane;
    }

    public void displayOne(Product product){
        this.name.setText(product.getName());
        this.description.setText(product.getDescription());
        this.amt.setText(product.getAmt_left() + "");
        this.price.setText(product.getPrice() + "");

    }
}
