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

        initLogin();
    }

    @Override
    public Pane display(){
        return flow;
    }

    private void initLogin(){
        Label user = new Label("UserName");
        TextField field = new TextField("Type something here");
        Label pass = new Label("Password");
        TextField pass_field = new TextField("Type something here");
        Button button = new Button("Login");
        Text err = new Text("Your password or login information is incorrect.");

        button.setOnAction( e ->{
            System.out.println(field.getText());
            if(controller.isEmployee(user.getText(), pass.getText())){
                display.displayNav();
            }else{
                flow.getChildren().add(err);
            }
        });

        flow.getChildren().addAll(user, field, pass, pass_field, button);
    }

}
