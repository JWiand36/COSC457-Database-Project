package services;

import display.*;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class Display {

    private BorderPane main;
    private Customer customer;
    private Employee employee;
    private Inventory inventory;
    private Navigation nav;
    private Sale sale;
    private SideBar side;
    private Controller controller;

    public Display(BorderPane main){
        this.main = main;
    }

    //Package Methods
    void initDisplay(){

        Login login = new Login(controller, this);

        this.customer = new Customer(controller);
        this.employee = new Employee(controller);
        this.inventory = new Inventory(controller);
        this.nav = new Navigation(this);
        this.sale = new Sale(controller);
        this.side = new SideBar(controller);

        main.setCenter(login.display());
    }

    void addController(Controller controller){
        this.controller = controller;
    }

    //Public Methods
    public void displayNav(){
        main.setTop(nav.displayNav(controller.isManager()));
        main.setCenter(sale.display());
    }

    public void display(DisplayInterface dis, boolean sideNeeded){
        main.setCenter(dis.display());

        if(sideNeeded)
            main.setRight(this.side.display());
        else
            main.setRight(null);
    }

    public Label makeMenuItem(String text){

        Label item = new Label(text);

        switch (text){
            case "Customer":
                item.setOnMouseClicked(e-> this.display(customer, true));
                break;
            case "Sales":
                item.setOnMouseClicked(e-> this.display(sale, false));
                break;
            case "Inventory":
                item.setOnMouseClicked(e-> this.display(inventory, true));
                break;
            case "Employee":
                item.setOnMouseClicked(e-> this.display(employee, true));
                break;
        }

        return item;
    }

    //Private Methods
}
