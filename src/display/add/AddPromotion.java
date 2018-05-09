package display.add;

import display.main.DisplayInterface;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import services.Controller;
import services.Display;
import structures.Promotion;

import java.util.ArrayList;

public class AddPromotion implements DisplayInterface {

    private GridPane pane = new GridPane();
    private ComboBox<String> items = new ComboBox<>();
    private TextField idField = new TextField();
    private TextField dateField = new TextField();
    private TextField discountField = new TextField();

    public AddPromotion(Controller controller){

        Label item = new Label("Item: ");
        Label date = new Label("Date: ");
        Label discount = new Label("Discount: ");
        Label id = new Label("ID: ");
        Button submit = new Button("Submit");

        ArrayList<String> list = controller.getData(Display.INVENTORY);
        items.setItems(FXCollections.observableArrayList(list));

        pane.add(id, 0,0);
        pane.add(idField, 1,0);
        pane.add(item, 0,1);
        pane.add(items, 1,1);
        pane.add(discount, 0,2);
        pane.add(discountField, 1,2);
        pane.add(date, 0,3);
        pane.add(dateField, 1,3);
        pane.add(submit, 0,4);

        submit.setOnAction(e->{
            Promotion promotion;
            if(isNumber(idField.getText())) {
                promotion = new Promotion(Integer.parseInt(idField.getText()), items.getSelectionModel().getSelectedItem(),
                        Double.parseDouble(discountField.getText()), dateField.getText());
            }else
                promotion = new Promotion(items.getSelectionModel().getSelectedItem(),
                        Double.parseDouble(discountField.getText()), dateField.getText());

            controller.addData(promotion, Display.PROMOTION);
        });
    }

    public void displayOne(Promotion promotion){
        items.getSelectionModel().select(promotion.getItem());
        idField.setText(promotion.getId()+"");
        dateField.setText(promotion.getDate());
        discountField.setText(promotion.getDiscount()+"");
    }

    @Override
    public Pane display() {
        return pane;
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
