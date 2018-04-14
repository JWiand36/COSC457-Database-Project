package display;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import services.Display;
import structures.Receipt;


public class Sale implements DisplayInterface{

    private GridPane pane;
    private Display display;

    private Text customer = new Text("Sale");
    private Text employee = new Text("Sale");


    public Sale(Display display){

        this.display = display;

        pane = new GridPane();
        this.pane.add(customer,0,0);
        this.pane.add(employee,0,1);



    }

    public void displayOne(Receipt receipt){
        this.employee.setText(receipt.getEmployee_name());
        this.customer.setText(receipt.getCustomer_name());
    }

    public Pane display(){
        return pane;
    }
}
