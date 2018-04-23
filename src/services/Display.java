package services;

import display.*;
import display.add.*;
import display.main.*;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import structures.DataInterface;
import structures.Product;
import structures.Receipt;

import java.util.ArrayList;

/*
 This class controls anything dealing with the display. If an action takes place that involve
 with the display of the program. It will be in this class. Also if data is going to be displayed
 then the data will pass through this class.
 */

public class Display {

    private BorderPane main;

    //The 4 main panes
    private Customer customer;
    private Employee employee;
    private Inventory inventory;
    private Sale sale;

    //The 4 add panes
    private AddCustomer add_customer;
    private AddProduct add_product;
    private AddEmployee add_employee;
    private AddSale add_sale;

    //Navigation and the List on the side
    private Navigation nav;
    private SideBar side;
    private VendorInfo vendor;
    private WarehouseInfo warehouse;

    private Controller controller;

    private int current_pane = 0;

    //Used to help determine location
    public static final int CUSTOMERS = 1;
    public static final int SALES = 2;
    public static final int INVENTORY = 3;
    public static final int EMPLOYEE = 4;
    static final int ADD_SALE = 5;
    public static final int WARHOUSE = 6;
    public static final int VENDOR = 7;

    public Display(BorderPane main){
        this.main = main;
    }

    //Initializes the display
    void initDisplay(){

        Login login = new Login(controller, this);

        this.customer = new Customer();
        this.employee = new Employee();
        this.inventory = new Inventory();
        this.sale = new Sale();

        this.add_customer = new AddCustomer(controller);
        this.add_employee = new AddEmployee(controller);
        this.add_product = new AddProduct(controller, this);
        this.add_sale = new AddSale(controller);

        this.nav = new Navigation(this);
        this.side = new SideBar(this);
        this.vendor = new VendorInfo();
        this.warehouse = new WarehouseInfo();

        main.setCenter(login.display());
    }

    void addController(Controller controller){
        this.controller = controller;
    }

    public void setStore(String store){ controller.setStore(store); }

    //Displays the navigation bar with the Sales panes as a default
    public void displayNav(){
        side.getStores(controller.getStores());

        this.current_pane = SALES;
        setInfo();

        main.setTop(nav.displayNav(controller.isManager()));
        main.setCenter(sale.display());
        main.setRight(side.display());

    }

    public void displaySideInfo(int num){
        if(num == WARHOUSE){
           if(main.getLeft() != null && main.getLeft() == warehouse.display())
               main.setLeft(null);
           else
               main.setLeft(warehouse.display());
        }else{
            if(main.getLeft() != null && main.getLeft() == vendor.display())
                main.setLeft(null);
            else
                main.setLeft(vendor.display());
        }
    }

    //General method used to display the main pain and the add pane. The integer is to hold the current pane
    //for data transfer.
    public void display(DisplayInterface dis, DisplayInterface add, int n){
        this.current_pane = n;
        setInfo();

        main.setCenter(dis.display());
        main.setLeft(null);

        if(add != null)
            main.setBottom(add.display());
        else
            main.setBottom(null);
    }

    //This method is used create the menu items at the top. Allowing users to navigate through the program.
    public Label makeMenuItem(String text){

        Label item = new Label(text);

        switch (text){
            case "Customer":
                item.setOnMouseClicked(e-> this.display(customer, add_customer, CUSTOMERS));
                break;
            case "Previous Sales":
                item.setOnMouseClicked(e-> this.display(sale, null, SALES));
                break;
            case "Inventory":
                item.setOnMouseClicked(e-> this.display(inventory, add_product, INVENTORY));
                break;
            case "Employee":
                item.setOnMouseClicked(e-> this.display(employee, add_employee, EMPLOYEE));
                break;
            case "New Sale":
                item.setOnMouseClicked(e-> this.display(add_sale, null, ADD_SALE));
                break;
        }

        return item;
    }

    //The method passes the data to the current pane. Only one object is passed and viewed at a time. The current
    //pane variable determines which pane is used.
    public void displayData(String data){

        DataInterface obj = controller.getOne(data, current_pane);

        if(current_pane == CUSTOMERS){
            customer.displayOne((structures.Customer) obj);
            add_customer.displayOne((structures.Customer) obj);
        }else if(current_pane == SALES){
            sale.displayOne((Receipt) obj);
        }else if(current_pane == INVENTORY){
            inventory.displayOne((Product) obj);
            add_product.displayOne((Product) obj);
            vendor.setData(controller.getData(data, VENDOR));
            warehouse.setData(controller.getData(data, WARHOUSE));
        }else if(current_pane == EMPLOYEE){
            employee.displayOne((structures.Employee) obj);
            add_employee.displayOne((structures.Employee) obj);
        }else if(current_pane == ADD_SALE){
            add_sale.displayOne((structures.Product) obj);
        }

    }

    public void setInfo(){
        side.setInfo(controller.getData(current_pane));
    }
}
