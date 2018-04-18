package display.add;

import display.main.DisplayInterface;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import services.Controller;
import services.Display;
import structures.Employee;

public class AddEmployee implements DisplayInterface{

    private GridPane pane;


    private TextField first_name = new TextField();
    private TextField last_name = new TextField();
    private TextField address = new TextField();
    private TextField ssn = new TextField();
    private TextField super_name = new TextField();
    private TextField age = new TextField();
    private TextField store = new TextField();

    public AddEmployee(Controller controller){

        this.pane = new GridPane();

        ComboBox<String> sex = new ComboBox<>();
        Button submit = new Button("Submit");
        Button edit = new Button("Edit");

        sex.getItems().addAll("Male", "Female");

        pane.add(new Label("First Name: "), 0,0);
        pane.add(new Label("Last Name: "), 2,0);
        pane.add(new Label("Address: "), 0,1);
        pane.add(new Label("Social: "), 2,1);
        pane.add(new Label("Sex: "), 0,2);
        pane.add(new Label("Supervisor's Name: "), 2,2);
        pane.add(new Label("Age: "), 0,3);
        pane.add(new Label("Store: "), 2,3);
        pane.add(first_name, 1,0);
        pane.add(last_name,3,0);
        pane.add(address,1,1);
        pane.add(ssn,3,1);
        pane.add(sex,1,2);
        pane.add(super_name,3,2);
        pane.add(age,1,3);
        pane.add(store,3,3);

        pane.add(edit, 1,4);
        pane.add(submit, 2 , 4);

        submit.setOnAction(e->{
            Employee employee = new Employee(super_name.getText() ,first_name.getText(),
                    last_name.getText(), address.getText(), sex.getSelectionModel().getSelectedItem(),
                    Integer.parseInt(age.getText()), store.getText());

            controller.addData(employee, Display.EMPLOYEE);
        });

        edit.setOnAction(e->{
            Employee employee = new Employee(Integer.parseInt(ssn.getText()), super_name.getText() ,first_name.getText(),
                    last_name.getText(), address.getText(), sex.getSelectionModel().getSelectedItem(),
                    Integer.parseInt(age.getText()), store.getText());

            controller.editData(employee, Display.EMPLOYEE);
        });


    }

    @Override
    public Pane display(){
        return pane;
    }

    public void displayOne(Employee employee){
        this.first_name.setText(employee.getFirst_name());
        this.last_name.setText(employee.getLast_name());
        this.address.setText(employee.getAddress());
        this.super_name.setText(employee.getSuper_Name());
        this.age.setText(employee.getAge()+"");
        this.store.setText(employee.getStore());
    }
}
