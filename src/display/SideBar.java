package display;


import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

import javafx.scene.layout.VBox;
import services.Display;

import java.util.ArrayList;

public class SideBar {

    private VBox pane;

    private ComboBox<String> store = new ComboBox<>();

    private ListView<String> list_view;

    public SideBar(Display display){

        this.pane = new VBox(5);

        list_view = new ListView<>();

        list_view.setOnMouseClicked(e-> {
            if(e.getClickCount() == 1)
                display.displayData(selectedData());
        });

        store.getSelectionModel().selectedItemProperty().addListener(e->{
            display.setStore(selectedStore());
            display.setInfo();
        });

        pane.getChildren().addAll(store, list_view);
    }

    public void setInfo(ArrayList<String> list){
        list_view.setItems(FXCollections.observableArrayList(list));
    }

    public void getStores(ArrayList<String> stores){ store.setItems(FXCollections.observableArrayList(stores)); }

    public Pane display(){
        return pane;
    }

    //Private Methods
    private String selectedData(){
        if(list_view.getSelectionModel().getSelectedIndex() != -1)
            return list_view.getItems().get(list_view.getSelectionModel().getSelectedIndex());
        else if(list_view.getItems().get(0) != null)
            return list_view.getItems().get(0);
        else
            return "";
    }

    private String selectedStore(){
        if(store.getSelectionModel().getSelectedIndex() != -1)
            return store.getItems().get(store.getSelectionModel().getSelectedIndex());
        else if(store.getItems().get(0) != null)
            return store.getItems().get(0);
        else
            return "";
    }
}
