package display.add;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import services.Controller;
import services.Display;
import structures.Customer;

public class AddCustomer implements AddInterface{

    private GridPane pane;

    public AddCustomer(Controller controller){

        this.pane = new GridPane();

        TextField first_name = new TextField();
        TextField last_name = new TextField();
        TextField address = new TextField();
        ComboBox<String> sex = new ComboBox<>();
        Button submit = new Button("Submit");

        sex.getItems().addAll("Male", "Female");

        pane.add(new Label("First Name:"), 0,0);
        pane.add(new Label("Last Name:"), 2,0);
        pane.add(new Label("Address:"), 0,1);
        pane.add(new Label("Sex:"), 2,1);
        pane.add(first_name, 1,0);
        pane.add(last_name,3,0);
        pane.add(address,1,1);
        pane.add(sex,3,1);

        pane.add(submit, 0 , 2);

        submit.setOnAction(e->{
            System.out.println(sex.getValue());
            Customer customer = new Customer(first_name.getText(), last_name.getText(),
                    address.getText(), sex.getSelectionModel().getSelectedItem());

            controller.addData(customer, Display.CUSTOMERS);
        });
    }

    @Override
    public Pane display(){
        return pane;
    }
}
