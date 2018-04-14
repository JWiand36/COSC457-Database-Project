package services;

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

    public boolean isEmployee(String user, String pass){
        return true;
    }

    private void initDisplay(){
        this.display.addController(this);
        this.display.initDisplay();
    }
}
