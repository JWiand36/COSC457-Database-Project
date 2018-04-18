package display.add;

import display.main.DisplayInterface;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import services.Controller;
import services.Display;
import structures.Product;
import structures.Receipt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class AddSale implements DisplayInterface{

    private Controller controller;

    private FlowPane pane;

    private ArrayList<Product> products = new ArrayList<>();
    private ArrayList<Button> buttons = new ArrayList<>();

    private Button submit = new Button("Submit");

    private TextField customer = new TextField();

    private Text total = new Text(0+"");

    //public Receipt(String customer_name, String employee_name, String store_location, Product[] products, String date) {


    public AddSale(Controller controller){

        this.pane = new FlowPane();

        this.controller = controller;

        submit.setOnAction(e->{
            Receipt receipt = new Receipt(customer.getText(), getEmployee(), getStore(), products, new Date().toString());

            controller.addData(receipt, Display.SALES);
        });

        buildPane(pane);
    }

    public void displayOne(Product product){
        products.add(product);

        Button button = new Button("Remove");

        button.setOnAction(e->{
            int num = buttons.indexOf(button);

            total.setText(subTotal(products.get(num).getPrice()));

            products.remove(num);
            buttons.remove(num);

            buildPane(pane);
        });

        buttons.add(button);

        buildPane(pane);

        total.setText(addTotal(product.getPrice()));
    }

    @Override
    public Pane display(){
        return pane;
    }

    private String getEmployee(){
        return controller.getEmployee();
    }

    private String getStore(){
        return controller.getStore();
    }

    private String addTotal(double amount){
        double temp = Double.parseDouble(total.getText());
        temp += amount;
        return temp +"";
    }

    private String subTotal(double amount){
        double temp = Double.parseDouble(total.getText());
        temp -= amount;
        return temp +"";
    }

    private void buildPane(FlowPane pane){
        pane.getChildren().clear();

        this.pane.getChildren().addAll(new Label("Customer Name: "),customer,new Label("Products: "));

        for( int i = 0; i < products.size(); i++)
            this.pane.getChildren().addAll(new Text(products.get(i).toString()),buttons.get(i));

        this.pane.getChildren().addAll(new Label("Total Cost: "),total,submit);
    }
}
