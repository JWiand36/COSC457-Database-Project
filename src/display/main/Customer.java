package display.main;


import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class Customer implements DisplayInterface {

    private GridPane pane;
    private Text name = new Text();
    private Text address = new Text();
    private Text sex = new Text();
    private Text balance = new Text();

    public Customer(){

        this.pane = new GridPane();

        this.pane.add(new Label("Name: "),0,0);
        this.pane.add(new Label("Address: "),0,1);
        this.pane.add(new Label("Sex: "),0,2);
        this.pane.add(new Label("Total Balance: "),0,3);

        this.pane.add(name,1,0);
        this.pane.add(address,1,1);
        this.pane.add(sex,1,2);
        this.pane.add(balance,1,3);
    }

    public void displayOne(structures.Customer customer){
        this.name.setText(customer.getFirst_name() + " " + customer.getLast_name());
        this.address.setText(customer.getAddress());
        this.sex.setText(customer.getSex());
        this.balance.setText(customer.getBalance()+"");
    }

    @Override
    public Pane display() {
        return pane;
    }
}
