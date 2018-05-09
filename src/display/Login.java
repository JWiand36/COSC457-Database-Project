package display;

import display.main.DisplayInterface;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import services.Controller;
import services.Display;

public class Login implements DisplayInterface {

    private FlowPane flow;
    private Controller controller;
    private Display display;

    public Login(Controller controller, Display display){

        this.display = display;
        this.controller = controller;
        flow = new FlowPane();

        flow.setHgap(5);

        initLogin();
    }

    @Override
    public Pane display(){
        return flow;
    }

    private void initLogin(){
        Label user = new Label("UserName");
        TextField user_field = new TextField("Type something here");
        Label pass = new Label("Password");
        TextField pass_field = new TextField("Type something here");
        Button button = new Button("Login");
        Text err = new Text();


        button.setOnAction( e ->{
            if(controller.isEmployee(user_field.getText(), pass_field.getText())){
                display.displayNav();
            }else{
                err.setText("Your password or login information is incorrect.");
            }
        });

        flow.getChildren().addAll(user, user_field, pass, pass_field, button);

        flow.getChildren().add(err);
    }

}
