package display.main;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import structures.Order;
import structures.Product;


public class ShipmentPane implements DisplayInterface{

    private GridPane pane = new GridPane();

    private Text customerTxt = new Text();
    private Text addressField = new Text();
    private Text itemField = new Text();
    private Text costField = new Text();
    private Text confirmField = new Text();
    private Text companyField = new Text();
    private Text shipCostField = new Text();

    public ShipmentPane(){

        Label customer = new Label("Customer: ");
        Label address = new Label("Shipping Address: ");
        Label items = new Label("Ordered Items: ");
        Label cost = new Label("Cost: ");
        Label confirmNumber = new Label("Confirmation Number: ");
        Label shipCompany = new Label("Shipping Company: ");
        Label shipCost = new Label("Shipping Cost: ");


        pane.add(customer, 0,0);
        pane.add(customerTxt,1,0);
        pane.add(address,0,1);
        pane.add(addressField,1,1);
        pane.add(items,0,2);
        pane.add(itemField,1,2);
        pane.add(cost,0,3);
        pane.add(costField,1,3);
        pane.add(confirmNumber,0,4);
        pane.add(confirmField,1,4);
        pane.add(shipCompany,0,5);
        pane.add(companyField,1,5);
        pane.add(shipCost,0,6);
        pane.add(shipCostField,1,6);

        pane.setVgap(5);
        pane.setHgap(5);

    }

    public void displayOne(Order order){

        String temp = "";

        customerTxt.setText(order.getCustomer());
        addressField.setText(order.getShipping_Address());

        for(Product product: order.getProducts()){
            temp += product.toString() + "\n";
        }

        itemField.setText(temp);
        costField.setText("" + order.getCost());
        confirmField.setText("" + order.getConfirmNumber());
        companyField.setText(order.getShipping_Company());
        shipCostField.setText("" + order.getShipping_Cost());


    }

    @Override
    public Pane display() {
        return pane;
    }
}
