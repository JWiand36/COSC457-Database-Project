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

        bar = new MenuBar(new_sales, customer, sale);
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
