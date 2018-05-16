package display.add;

import display.main.DisplayInterface;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import services.Controller;
import services.Display;
import structures.Order;
import structures.Product;

import java.util.ArrayList;

public class AddOrder implements DisplayInterface{

    private Controller controller;
    private VBox pane;
    private ArrayList<Product> products = new ArrayList<>();
    private ArrayList<Button> buttons = new ArrayList<>();
    private Button submit = new Button("Submit");
    private TextField shippingAddress = new TextField();
    private TextField creditNum = new TextField();
    private TextField creditName = new TextField();
    private ComboBox<String> customer = new ComboBox<>();
    private CheckBox credit = new CheckBox();
    private Text total = new Text(0+"");

    public AddOrder(Controller controller){

        this.pane = new VBox(5);
        this.controller = controller;

        submit.setOnAction(e->{
            Order order = new Order();

            if(shippingAddress.getText().equals("")){
                System.out.println("Ran");
                shippingAddress.setText("In Store");
            }

            String selectCutomer = customer.getSelectionModel().getSelectedItem();
            if(isNumber(creditNum.getText()))
                order = new Order(selectCutomer, Double.parseDouble(total.getText()), shippingAddress.getText(), products,
                    Integer.parseInt(creditNum.getText()), creditName.getText(), credit.isSelected());
            else
                order = new Order(selectCutomer, Double.parseDouble(total.getText()), shippingAddress.getText(), products,
                        0, creditName.getText(), credit.isSelected());
            controller.addData(order, Display.ADDORDER);
        });

        shippingAddress.setMaxWidth(250);
        creditNum.setMaxWidth(250);
        creditName.setMaxWidth(250);
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

        this.pane.getChildren().addAll(new Label("Customer: "), customer, new Label("Shipping Address: "),shippingAddress,new Label("Products: "));
        this.pane.getChildren().addAll(new Label("Credit Card Number: "), creditNum, new Label("Name of Card: "), creditName);

        for( int i = 0; i < products.size(); i++)
            this.pane.getChildren().addAll(new Text(products.get(i).toString()),buttons.get(i));

        this.pane.getChildren().addAll(new Label("Total Cost: "),total,new Label("Credit to Account: "),credit,submit);
    }

    public void setInfo(ArrayList<String> list){
        customer.setItems(FXCollections.observableArrayList(list));
    }

    private boolean isNumber(String s){

        char a;
        int c = 0;

        for(int i = 0; i < s.length(); i++){

            a = s.charAt(i);

            if(!Character.isDigit(a))
                c++;
        }

        return c <= 0 && s.length() != 0;
    }
}
