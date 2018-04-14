package services;

import structures.DataInterface;

import java.util.ArrayList;

public class Controller {

    private Storage storage;
    private Display display;

    public Controller(Display display, Storage storage){
        this.storage = storage;
        this.display = display;
        initDisplay();
    }


    boolean isManager(){
        return storage.isManager();
    }

    ArrayList<String> getData(int n){
        return storage.getData(n);
    }

    public boolean isEmployee(String user, String pass){
        return true;
    }

    DataInterface getOne(String data, int location){
        return storage.getOne(data, location);
    }

    private void initDisplay(){
        this.display.addController(this);
        this.display.initDisplay();
    }
}
