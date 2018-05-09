package display.add;

import display.main.DisplayInterface;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import services.Controller;
import services.Display;
import structures.Order;
import structures.Product;
import structures.Receipt;

import java.util.ArrayList;
import java.util.Date;

public class AddOrder implements DisplayInterface{

    private Controller controller;

    private VBox pane;

    private ArrayList<Product> products = new ArrayList<>();
    private ArrayList<Button> buttons = new ArrayList<>();

    private Button submit = new Button("Submit");

    private TextField shippingAddress = new TextField();

    private Text total = new Text(0+"");

    public AddOrder(Controller controller){

        this.pane = new VBox(5);

        this.controller = controller;

        submit.setOnAction(e->{
            Order order = new Order(Double.parseDouble(total.getText()), shippingAddress.getText(), products);
            controller.addData(order, Display.ADDORDER);
        });

        shippingAddress.setMaxWidth(250);

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

    private String getStore(){
        return controller.getOneStore();
    }

    private String addTotal(double amount){
        double temp = Double.parseDouble(total.getText());
        temp += amount;
        return String.format("%1$.2f",temp);
    }

    private String subTotal(double amount){
        double temp = Double.parseDouble(total.getText());
        temp -= amount;
        return String.format("%1$.2f",temp);
    }

    private void buildPane(VBox pane){
        pane.getChildren().clear();

        this.pane.getChildren().addAll(new Label("Shipping Address: "),shippingAddress,new Label("Products: "));

        for( int i = 0; i < products.size(); i++)
            this.pane.getChildren().addAll(new Text(products.get(i).toString()),buttons.get(i));

        this.pane.getChildren().addAll(new Label("Total Cost: "),total,submit);
    }
}
