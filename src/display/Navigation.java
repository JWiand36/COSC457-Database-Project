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
        Menu sale = new Menu("", display.makeMenuItem("Previous Sales"));
        Menu new_sales = new Menu("", display.makeMenuItem("New Sale"));
        Menu events = new Menu("", display.makeMenuItem("Events"));
        Menu promos = new Menu("", display.makeMenuItem("Promotions"));
        Menu order = new Menu("", display.makeMenuItem("Orders"));
        Menu addorder = new Menu("", display.makeMenuItem("Make Order"));
        Menu ship = new Menu("", display.makeMenuItem("Shipment"));

        bar = new MenuBar(new_sales, sale, customer, events, promos, order, addorder, ship);
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
