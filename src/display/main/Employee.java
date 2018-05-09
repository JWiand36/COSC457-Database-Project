package display.main;

import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class Employee implements DisplayInterface {

    private GridPane pane;

    private Text name = new Text();
    private Text address = new Text();
    private Text sex = new Text();
    private Text age = new Text();
    private Text super_name = new Text();
    private Text store = new Text();

    public Employee(){

        this.pane = new GridPane();

        this.pane.add(new Label("Name: "),0,0);
        this.pane.add(new Label("Address: "),0,1);
        this.pane.add(new Label("Sex: "),0,2);
        this.pane.add(new Label("Age: "),0,3);
        this.pane.add(new Label("Supervisor: "),0,4);
        this.pane.add(new Label("Store: "),0,5);

        this.pane.add(name,1,0);
        this.pane.add(address,1,1);
        this.pane.add(sex,1,2);
        this.pane.add(age,1,3);
        this.pane.add(super_name,1,4);
        this.pane.add(store,1,5);

        pane.getColumnConstraints().add(new ColumnConstraints(200));
        pane.setHgap(5);
        pane.setVgap(5);

    }

    public void displayOne(structures.Employee employee){
        this.name.setText(employee.getFirst_name() + " " + employee.getLast_name());
        this.address.setText(employee.getAddress());
        this.sex.setText(employee.getSex());
        this.age.setText(employee.getAge() + "");
        this.super_name.setText(employee.getSuper_Name());
        this.store.setText(employee.getStore());
    }

    @Override
    public Pane display(){
        return pane;
    }
}
