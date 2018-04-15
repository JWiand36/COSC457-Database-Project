package display.main;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class Employee implements DisplayInterface {

    private GridPane pane;

    private Text name = new Text("Employee");

    public Employee(){

        this.pane = new GridPane();


        this.pane.add(name,0,0);

    }

    public void displayOne(structures.Employee employee){
        this.name.setText(employee.getFirst_name() + " " + employee.getLast_name());
    }

    @Override
    public Pane display(){
        return pane;
    }
}
