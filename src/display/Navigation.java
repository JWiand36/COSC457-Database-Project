package display;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import services.Display;

public class Navigation {

    private MenuBar bar;
    private Display display;

    public Navigation(Display display){
        this.display = display;
        initNavigation();
    }

    private void initNavigation(){
        Menu customer = new Menu("",display.makeMenuItem("Customer"));
        Menu events = new Menu("", display.makeMenuItem("Events"));
        Menu promos = new Menu("", display.makeMenuItem("Promotions"));
        Menu order = new Menu("", display.makeMenuItem("Sale"));
        Menu addorder = new Menu("", display.makeMenuItem("Make Sale"));
        Menu ship = new Menu("", display.makeMenuItem("Shipment"));

        bar = new MenuBar(order, addorder, customer, events, promos, ship);
    }

    public MenuBar displayNav(boolean manager){

        if(manager){
            Menu employee = new Menu("", display.makeMenuItem("Employee"));
            Menu inventory = new Menu("", display.makeMenuItem("Inventory"));

            bar.getMenus().addAll(inventory, employee);
        }

        return bar;
    }
}
