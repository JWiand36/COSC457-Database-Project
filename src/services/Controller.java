package services;

import structures.Customer;
import structures.DataInterface;
import structures.Employee;
import structures.Product;

import java.util.ArrayList;

import static services.Display.*;

/*
 This class deals with any type of data transfer. If data is being transferred to the display, the controller will
 handle the action. If data is being added to the storage, the controller will deal with the actions taken place.
 */

public class Controller {

    private Storage storage;
    private Display display;

    public Controller(Display display, Storage storage){
        this.storage = storage;
        this.display = display;
        initDisplay();
    }

    //Used to help determine if an employee is a manager
    boolean isManager(){
        return storage.isManager();
    }

    //Transfers a list of data for the side bar.
    ArrayList<String> getData(int n){
        return storage.getData(n);
    }

    public String getEmployee(){ return storage.getEmployee(); }

    public String getStore(){ return storage.getStore(); }

    //determine's if the login is correct.
    public boolean isEmployee(String user, String pass){
        return storage.isEmployee(user, pass);
    }

    public void addData(DataInterface data, int current_pane){
        storage.addData(data, current_pane);
    }

    public void editData(DataInterface data, int current_pane){
        storage.editData(data, current_pane);

        if(current_pane == CUSTOMERS){
            display.displayData(((Customer) data).getFirst_name());
        }else if(current_pane == EMPLOYEE){
            display.displayData(((Employee) data).getFirst_name());
        }else if(current_pane == INVENTORY) {
            display.displayData(((Product) data).getName());
        }
    }

    public void addAmt(int amount){
        storage.addAmt(amount);
    }

    public void subAmt(int amount){
        storage.subAmt(amount);
    }

    //Transfers one entity to the display
    DataInterface getOne(String data, int current_pane){
        return storage.getOne(data, current_pane);
    }

    //Helps sets up the display controller
    private void initDisplay(){
        this.display.addController(this);
        this.display.initDisplay();
    }
}
