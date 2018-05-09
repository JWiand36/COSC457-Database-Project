package display.add;

import display.main.DisplayInterface;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import services.Controller;
import services.Display;
import structures.Event;

public class AddEvent implements DisplayInterface {

    GridPane pane = new GridPane();

    TextField nameField = new TextField();
    TextField dateField = new TextField();
    TextField hostField = new TextField();

    public AddEvent(Controller controller){
        Label nameLbl = new Label("Name: ");
        Label dateLbl = new Label("Date: ");
        Label hostLbl = new Label("Host Name: ");
        Button submit = new Button("Submit");

        pane.add(nameLbl, 0,0);
        pane.add(nameField,1,0);
        pane.add(hostLbl, 0,1);
        pane.add(hostField,1,1);
        pane.add(dateLbl, 0,2);
        pane.add(dateField,1,2);
        pane.add(submit, 0,3);

        submit.setOnAction(e->{
            Event event = new Event(nameField.getText(), dateField.getText(), hostField.getText());

            controller.addData(event, Display.EVENT);
        });
    }

    public void displayOne(Event event){
        nameField.setText(event.getName());
        hostField.setText(event.getHost());
        dateField.setText(event.getDate());
    }

    @Override
    public Pane display() {
        return pane;
    }
}
