package display.add;

import display.main.DisplayInterface;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import services.Controller;
import services.Display;
import structures.Product;

public class AddProduct implements DisplayInterface{

    private GridPane pane;

    private TextField name = new TextField();
    private TextField description = new TextField();
    private TextField amt = new TextField();
    private TextField price = new TextField();

    public AddProduct(Controller controller){

        this.pane = new GridPane();
        Button submit = new Button("Submit");
        Button edit = new Button("Edit");
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

        pane.add(submit, 3,2);
        pane.add(edit, 2, 2);
        pane.add(remove, 1, 2);
        pane.add(add, 0,2);

       // public Product(String name, String description, int amt_left, double price){


        submit.setOnAction(e->{
            Product product = new Product(name.getText(), description.getText(), Integer.parseInt(amt.getText()),
                    Double.parseDouble(price.getText()));

            controller.addData(product, Display.INVENTORY);
        });

        edit.setOnAction(e->{
            Product product = new Product(name.getText(), description.getText(), Integer.parseInt(amt.getText()),
                    Double.parseDouble(price.getText()));

            controller.editData(product, Display.INVENTORY);
        });

        add.setOnAction(e->{
            controller.addAmt(Integer.parseInt(amt.getText()));
        });

        remove.setOnAction(e->{
            controller.subAmt(Integer.parseInt(amt.getText()));
        });
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
