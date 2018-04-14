package services;

import display.*;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import structures.DataInterface;
import structures.Receipt;

public class Display {

    private BorderPane main;
    private Customer customer;
    private Employee employee;
    private Inventory inventory;
    private Navigation nav;
    private Sale sale;
    private SideBar side;
    private Controller controller;

    private int location = 0;

    static final int CUSTOMERS = 1;
    static final int SALES = 2;
    static final int INVENTORY = 3;
    static final int EMPLOYEE = 4;

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
        this.sale = new Sale(this);
        this.side = new SideBar(this);

        main.setCenter(login.display());
    }

    void addController(Controller controller){
        this.controller = controller;
    }

    //Public Methods
    public void displayNav(){
        side.setInfo(controller.getData(SALES));

        this.location = SALES;

        main.setTop(nav.displayNav(controller.isManager()));
        main.setCenter(sale.display());
        main.setRight(side.display());
    }

    public void display(DisplayInterface dis, int n){
        side.setInfo(controller.getData(n));

        this.location = n;

        main.setCenter(dis.display());
    }

    public Label makeMenuItem(String text){

        Label item = new Label(text);

        switch (text){
            case "Customer":
                item.setOnMouseClicked(e-> this.display(customer, CUSTOMERS));
                break;
            case "Sales":
                item.setOnMouseClicked(e-> this.display(sale, SALES));
                break;
            case "Inventory":
                item.setOnMouseClicked(e-> this.display(inventory, INVENTORY));
                break;
            case "Employee":
                item.setOnMouseClicked(e-> this.display(employee, EMPLOYEE));
                break;
        }

        return item;
    }

    public void displayData(String data){

        DataInterface obj = controller.getOne(data, location);

        if(location == CUSTOMERS){

        }else if(location == SALES){
            sale.displayOne((Receipt) obj);
        }else if(location == INVENTORY){

        }else if(location == EMPLOYEE){

        }

    }

    //Private Methods
}
