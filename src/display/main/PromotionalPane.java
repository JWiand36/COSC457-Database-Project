package display.main;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import structures.Promotion;

public class PromotionalPane implements DisplayInterface {

    private VBox pane = new VBox(5);
    private Text item_name = new Text("Item: ");
    private Text discount = new Text("Discount: ");
    private Text date = new Text("End Date: ");

    public PromotionalPane(){
        pane.getChildren().addAll(item_name, discount, date);
    }

    public void displayOne(Promotion promotion) {
        this.item_name.setText("Item: " + promotion.getItem());
        this.discount.setText("Discount: " + promotion.getDiscount());
        this.date.setText("End Date: " + promotion.getDate());
    }

    @Override
    public Pane display() {
        return pane;
    }
}
