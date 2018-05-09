package display.main;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import structures.Order;
import structures.Product;


public class OrdersPane implements DisplayInterface{

    private GridPane pane = new GridPane();

    private Text addressField = new Text();
    private Text itemField = new Text();
    private Text costField = new Text();
    private Text confirmField = new Text();
    private Text companyField = new Text();
    private Text shipCostField = new Text();

    public OrdersPane(){

        Label address = new Label("Shipping Address: ");
        Label items = new Label("Ordered Items: ");
        Label cost = new Label("Cost: ");
        Label confirmNumber = new Label("Confirmation Number: ");
        Label shipCompany = new Label("Shipping Company: ");
        Label shipCost = new Label("Shipping Cost: ");


        pane.add(address,0,0);
        pane.add(addressField,1,0);
        pane.add(items,0,1);
        pane.add(itemField,1,1);
        pane.add(cost,0,2);
        pane.add(costField,1,2);
        pane.add(confirmNumber,0,3);
        pane.add(confirmField,1,3);
        pane.add(shipCompany,0,4);
        pane.add(companyField,1,4);
        pane.add(shipCost,0,5);
        pane.add(shipCostField,1,5);

    }

    public void displayOne(Order order){

        String temp = "";

        addressField.setText(order.getShipping_Address());

        for(Product product: order.getProducts()){
            temp += product.toString() + "\n";
        }

        itemField.setText(temp);
        costField.setText(order.getCost()+"");
        confirmField.setText(order.getConfirmNumber()+"");
        companyField.setText(order.getShipping_Company());
        shipCostField.setText(order.getShipping_Cost()+"");


    }

    @Override
    public Pane display() {
        return pane;
    }
}
