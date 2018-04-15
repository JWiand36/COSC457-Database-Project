package display.main;


import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class Customer implements DisplayInterface {

    private GridPane pane;
    private Text name = new Text("Customer");

    public Customer(){

        this.pane = new GridPane();

        this.pane.add(name,0,0);
    }

    public void displayOne(structures.Customer customer){
        this.name.setText(customer.getFirst_name() + " " + customer.getLast_name());
    }

    @Override
    public Pane display() {
        return pane;
    }
}
