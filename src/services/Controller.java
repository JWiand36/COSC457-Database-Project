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
    public ArrayList<String> getData(int n){
        return storage.getData(n);
    }

    ArrayList<String> getData(String data, int n){
        return storage.getData(data, n);
    }

    ArrayList<String> getStores(){ return storage.getStore(); }

    public String getOneStore(){ return storage.getOneStore(); }


    //determine's if the login is correct.
    public boolean isEmployee(String user, String pass){
        return storage.isEmployee(user, pass);
    }

    public void addData(DataInterface data, int current_pane){
        storage.addData(data, current_pane);
    }

    public void addAmt(int amount, Product product){
        storage.addAmt(amount, product);
    }

    public void subAmt(int amount, Product product){
        storage.subAmt(amount, product);
    }

    void setStore(String store){ storage.setStore(store);}

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
