package services;

import structures.DataInterface;

import java.util.ArrayList;

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

    //determine's if the login is correct.
    public boolean isEmployee(String user, String pass){
        return storage.isEmployee();
    }

    public void addData(DataInterface data, int current_pane){
        storage.addData(data, current_pane);
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
