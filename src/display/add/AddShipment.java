package display.add;

import display.main.DisplayInterface;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import services.Controller;
import structures.Company;
import structures.Order;

import java.util.ArrayList;

public class AddShipment implements DisplayInterface {

    private Order order;
    private Company company;
    private GridPane pane = new GridPane();

    public AddShipment(Controller controller){

        Label shipment = new Label("Shipment: ");
        ArrayList<String> list = controller.getData(13); //Gets the shipping companies
        Text price = new Text("Price: ");
        ComboBox<String> companies = new ComboBox<>();
        Button submit = new Button("Submit");

        companies.setItems(FXCollections.observableArrayList(list));

        companies.getSelectionModel().selectedItemProperty().addListener(e->{
            company = controller.getOneCompany(companies.getSelectionModel().getSelectedItem());
            price.setText("Price: "+company.getCost());
        });

        submit.setOnAction(e-> order.setCompany(company));

        pane.add(shipment, 0,0);
        pane.add(companies,1,0);
        pane.add(price, 2,0);
        pane.add(submit,0,1);

    }

    @Override
    public Pane display() {
        return pane;
    }

    public void displayOne(Order order){
        this.order = order;
    }
}
